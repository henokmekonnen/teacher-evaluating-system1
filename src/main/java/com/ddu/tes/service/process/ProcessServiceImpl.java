package com.ddu.tes.service.process;

import com.ddu.tes.data.enums.ChannelEnum;
import com.ddu.tes.data.enums.ProcessRelationTypeEnum;
import com.ddu.tes.data.enums.ProcessStatusEnum;
import com.ddu.tes.data.enums.ProcessTypeEnum;
import com.ddu.tes.data.modle.Process;
import com.ddu.tes.data.modle.ProcessData;
import com.ddu.tes.data.modle.User;
import com.ddu.tes.data.repository.SqlRepository;
import com.ddu.tes.security.SecurityInfoMgnImpl;
import com.ddu.tes.service.user.UserService;
import com.ddu.tes.utils.CustomMessageService;
import com.ddu.tes.utils.RandomNumberGenerator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProcessServiceImpl implements ProcessService {

    private static final int DEFAULT_PROCESS_CODE_LENGTH = 19;
    private static final org.apache.commons.logging.Log logger = LogFactory.getLog(ProcessServiceImpl.class);

    @Autowired
    UserService userService;

    @Autowired
    private SqlRepository sqlRepository;

    @Autowired
    private SecurityInfoMgnImpl securityInfoManager;
    
    @Autowired
    private CustomMessageService customMessageService;


    @Override
    public Process registerPendingProcess(ProcessTypeEnum processTypeId, Map<String, String> processData, ChannelEnum processChannel,Integer initiatingUserId, Integer executingUserId, Integer processCodeLength) {
        try {

            Process process = new Process();
            process.setProcessTypeId(processTypeId);
            process.setProcessStatusId(ProcessStatusEnum.PENDING);
            process.setCreatedDate(Calendar.getInstance().getTime());
            process.setProcessChannelId(processChannel);
             process.setInitiatingUserId(initiatingUserId != null ? initiatingUserId : null);
            process.setExecutingUserId(executingUserId != null ? executingUserId : null);
            process.setCreatedByUserId(securityInfoManager.getCurrentUser() != null ? securityInfoManager.getCurrentUser().getUserId() : executingUserId);
            process.setStartedTimestamp(Calendar.getInstance().getTime());
            process.setStatusMessage(ProcessRelationTypeEnum.REGISTER_PROGRESS.toString());

            processCodeLength = processCodeLength == null ? DEFAULT_PROCESS_CODE_LENGTH : processCodeLength;

            long processCode = generateProcessCode(processCodeLength);

            process.setCode(String.valueOf(processCode));

            process = (Process) sqlRepository.insert(process);

            if (processData != null && !processData.isEmpty()) {

                List<Object> processDataList = new ArrayList<Object>();

                int processId = (Integer) process.getId();
                for (Map.Entry<String, String> entry : processData.entrySet()) {

                    ProcessData pData = new ProcessData();
                    pData.setDataName(entry.getKey());
                    pData.setDataValue(entry.getValue());
                    pData.setProcessId(processId);
                    pData.setCreatedByUserId(securityInfoManager.getCurrentUser().getUserId());
                    pData.setCreatedDate(Calendar.getInstance().getTime());
                      processDataList.add(pData);

                }
                sqlRepository.bulkInsert(processDataList);

            }

            return process;

        } catch (Exception ex) {
            logger.error("Error registering process, process type " + processTypeId, ex);

            throw ex;
        }
    }


    @Override
    @Transactional
    public Process completePendingProcess(Integer processId, String statusMessage) {

        try {

            if (processId == null) {
                
                throw new RuntimeException(" invalid null processeId");
            }

//            Process process = getProcessByProcessId(processId);
            Process process = new Process();
            process.setProcessId(processId);

            process=(Process)sqlRepository.findOne(process);
            if (process == null) {

                throw new RuntimeException(" invalid processe Model");            }


            if (!process.getProcessStatusId().equals(ProcessStatusEnum.PENDING)) {

                throw new RuntimeException(" invalid processe");            }

            process.setProcessStatusId(ProcessStatusEnum.SUCCESSFUL);
            process.setCompletedTimestamp(Calendar.getInstance().getTime());
            process.setStatusMessage(statusMessage != null ? statusMessage : null);
            sqlRepository.update(process);

            return process;

        } catch (Exception ex) {

            logger.error("Error while completing process", ex);
            throw ex;
        }
    }


    @Override
    @Transactional
    public void invalidateProcess(Integer processId, String statusMessage) {

        try {

            if (processId == null) {
                throw new RuntimeException(" invalid processe");            }

            Process process = (Process) sqlRepository.get(Process.class, processId, null);

            if (process == null) {
                return;

            }

            process.setProcessStatusId(ProcessStatusEnum.INVALID);
            process.setCompletedTimestamp(Calendar.getInstance().getTime());
            process.setStatusMessage(statusMessage);
            sqlRepository.update(process);

        } catch (Exception ex) {

            logger.error("Error while invalidating process data for process id" + processId, ex);
            throw ex;
        }

    }


    @Override
    @Transactional
    public void cancelProcess(Integer processId, String statusMessage) {

        try {

            if (processId == null) {
                throw new RuntimeException(" invalid processe");            }

            Process process = (Process) sqlRepository.get(Process.class, processId, null);

            if (process == null) {

                //todo throw Ex
            }

            process.setProcessStatusId(ProcessStatusEnum.CANCELLED);
            process.setCompletedTimestamp(Calendar.getInstance().getTime());
            process.setStatusMessage(statusMessage);
            sqlRepository.update(process);

        } catch (Exception ex) {

            logger.error("Error while cancelling process data for process id" + processId, ex);
            throw ex;
        }

    }
@Override
    public Process getPendingProcessByProcessCodeAndProcessType(String processCode, ProcessTypeEnum processType) {

        try {

            if (StringUtils.isBlank(processCode)) {
                throw new RuntimeException(" invalid processe");            }

            Process filter = new Process();
            filter.setCode(processCode);
            filter.setProcessTypeId(processType);
            filter.setProcessStatusId(ProcessStatusEnum.PENDING);

            final Object result = sqlRepository.findOne(filter);

            return null == result ? null : (Process) result;

        } catch (Exception ex) {

            logger.error("Error getting process, for process code" + processCode, ex);

            throw ex;
        }

    }

    @Override
    public Process getProcessByCode(String processCode) {

        try {

            if (StringUtils.isBlank(processCode)) {
                throw new RuntimeException(" invalid processe");            }

            Process filter = new Process();
            filter.setCode(processCode);

            final Object result = sqlRepository.findOne(filter);

            return null == result ? null : (Process) result;

        } catch (Exception ex) {
            logger.error("Error getting process, for process code" + processCode, ex);

            throw ex;
        }

    }

    @Override
    public Process getProcessByProcessId(Integer processId) {

        try {

            if (processId == null) {
                throw new RuntimeException(" invalid processe");            }
            ProcessData filterProcessData = new ProcessData();
filterProcessData.setProcessId(processId);

            Process result =(Process)sqlRepository.findOne(filterProcessData);
//            final Object result =(Optional) sqlRepository.get(Process.class, , null);

            return null == result ? null :(Process) result;

        } catch (Exception ex) {
            logger.error("Error getting process, for process id" + processId, ex);

            throw ex;
        }


    }


    @Override
    public Map<String, String> getProcessData(Integer processId) {

        try {

            HashMap<String, String> data = new HashMap<String, String>();

            ProcessData processData = new ProcessData();
            processData.setProcessId(processId);

            final List<Object> findResult = sqlRepository.find(processData);

            if (!findResult.isEmpty()) {

                for (Object obj : findResult) {

                    ProcessData item = (ProcessData) obj;
                    data.put(item.getDataName(), item.getDataValue());
                }
            }

            return data;
        } catch (Exception ex) {

            logger.error("Error while getting process data for process id" + processId, ex);
            throw ex;
        }
    }

    @Override
    public void addProcessData(int pendingProcessId, Map<String, String> data) {

        try {

            List<Object> processDataList = new ArrayList<>();

            for (Map.Entry<String, String> datum : data.entrySet()) {

                ProcessData processData = new ProcessData();
                processData.setProcessId(pendingProcessId);
                processData.setDataName(datum.getKey());
                processData.setDataValue(datum.getValue());

                processDataList.add(processData);
            }

            sqlRepository.bulkInsert(processDataList);

        } catch (Exception ex) {

            logger.error("Error while adding process data for process id" + pendingProcessId, ex);
            throw ex;
        }
    }


    @Override
    public void setProcessDatum(int processId, String name, String value) {

        try {

            ProcessData filter = new ProcessData();
            filter.setProcessId(processId);
            filter.setDataName(name);

            ProcessData datum = (ProcessData) sqlRepository.findOne(filter);

            if (datum == null) {

                ProcessData processData = new ProcessData();
                processData.setProcessId(processId);
                processData.setDataName(name);
                processData.setDataValue(value);

                sqlRepository.insert(processData);

            } else {

                datum.setDataValue(value);
                sqlRepository.update(datum);

            }
        } catch (Exception ex) {
            logger.error("Error while adding process data for process id" + processId);
            throw ex;
        }


    }

    @Override
    public long generateProcessCode(int processCodeLength) {

        try {

            if (processCodeLength <= 0) {

                //todo throw exception new ExceptionMessage()
            }

            return Long.valueOf(RandomNumberGenerator.generateNumber(processCodeLength));
        } catch (Exception ex) {
            logger.error("Error while generating process code", ex);
            throw ex;
        }
    }


    @Override
    public void updateProcess(Process process) {

        try {

            sqlRepository.update(process);

        } catch (Exception ex) {
            logger.error("Error updating processCode", ex);

            throw ex;
        }
    }


    @Override
    public Process getProcessByInitiatingUserAndProcessType(User user, ProcessTypeEnum processTypeEnum) {

        try {

            Process filter = new Process();
            filter.setInitiatingUserId(user.getUserId());
            filter.setProcessTypeId(processTypeEnum);

            Process process = (Process) sqlRepository.findOne(filter);

            return process != null ? process : null;


        } catch (Exception ex) {

            logger.error("Error fetching user with initiating user id " + user.getUserId() + " and process type " + processTypeEnum);
            throw ex;
        }
    }


}
