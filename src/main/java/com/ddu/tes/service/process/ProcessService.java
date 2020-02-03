package com.ddu.tes.service.process;


import com.ddu.tes.data.enums.ChannelEnum;
import com.ddu.tes.data.enums.ProcessTypeEnum;
import com.ddu.tes.data.modle.User;
import com.ddu.tes.data.modle.Process;

import java.util.Map;

public interface ProcessService {

    public Process registerPendingProcess(ProcessTypeEnum processTypeId, Map<String, String> processData, ChannelEnum processChannel, Integer initiatingUserId, Integer executingUserId, Integer processCodeLength);

    public Process completePendingProcess(Integer processId, String statusMessage);

    public void invalidateProcess(Integer processId, String statusMessage);

    public void cancelProcess(Integer processId, String statusMessage);

    public Process getPendingProcessByProcessCodeAndProcessType(String processCode, ProcessTypeEnum processType);

    public Process getProcessByCode(String processCode);

    public Process getProcessByProcessId(Integer processId);

    public Map<String, String> getProcessData(Integer processId);

    public void addProcessData(int pendingProcessId, Map<String, String> data);

    public void setProcessDatum(int processId, String name, String value);

    public long generateProcessCode(int processCodeLength);

    public void updateProcess(Process process);

    public Process getProcessByInitiatingUserAndProcessType(User user, ProcessTypeEnum processTypeEnum);


}
