$(document).ready(function () {

    $('#take-pic').hide();

    var navListItems = $('div.setup-panel div a'),
        allWells = $('.setup-content'),
        allNextBtn = $('.nextBtn'),
        allPrevBtn = $('#prevBtn'),
        allPrevBtn2 = $('#prevBtn2');

    allWells.hide();
    var stepOneCleared = false;
    var stepTwoCleared = false;


    navListItems.click(function (e) {

        e.preventDefault();
        var $target = $($(this).attr('href')),
            $item = $(this);


        if (!$item.hasClass('disabled')) {
            navListItems.removeClass('btn-primary').addClass('btn-default');
            $item.addClass('btn-default');
            allWells.hide();
            $target.show();
            $target.find('select:eq(0)').focus();
        }
    });

    allNextBtn.click(function () {

        var curStep = $(this).closest(".setup-content"),

            curStepBtn = curStep.attr("id"),
            nextStepWizard = $('div.setup-panel div a[href="#' + curStepBtn + '"]').parent().next().children("a"),
            isValid = true;


        if (curStepBtn == "step-1") {

            var isNameValid = validFirstName();
            var isGenderValid = validateGender();
            var isPhoneNumberValid = validatePhoneNumber();
            var isPhotoValid = validatePhoto();
            var isValidCountry = validateCountry();
            var isRegonValid = validateRegion();
            var isSubCityValid = validateSubcity();
            var isKebeleValid = validatekebele();
            var isHouseNoValid = validateHouseNo();
            var isStateValid = validateState();
            var isValidateCity = validateCity();
            var isEmailValid = validateEmail();
            var isEduValid  = validateEducation();
            var isOccupationValud  = validateOccupation();

            if (!isNameValid) {
                isValid = false;
                $("#name").addClass("form-error");

            }

            if (!isGenderValid) {
                isValid = false;
                $("#gender").addClass("form-error");

            }


            if (!isEmailValid) {
                isValid = false;
                $("#email").addClass("form-error");

            }


            if (!isEduValid) {
                isValid = false;
                $("#eduLevel").addClass("form-error");

            }




            if (!isPhoneNumberValid) {
                isValid = false;
                $("#phoneNumber").addClass("form-error");

            }


            if (!isPhotoValid) {
                isValid = false;
                $("#btn_snap").addClass("form-error");
                $('#take-pic').show();
            }


            if (!isValidCountry) {
                isValid = false;
                $("#country").addClass("form-error");

            }



            if (!isOccupationValud) {
                isValid = false;
                $("#occupation").addClass("form-error");

            }


            if (!isRegonValid) {
                isValid = false;
                $("#region").addClass("form-error");

            }

            if (!isSubCityValid) {
                isValid = false;
                $("#subcity").addClass("form-error");
            }


            if (!isKebeleValid) {
                isValid = false;
                $("#kebele").addClass("form-error");
            }


            if (!isHouseNoValid) {
                isValid = false;
                $("#houseNumber").addClass("form-error");

            }


            if (!isStateValid) {
                isValid = false;
                $("#state").addClass("form-error");

            }

            if (!isValidateCity) {
                isValid = false;
                $("#city").addClass("form-error");

            }
            stepOneCleared = true;

            if (isValid) {

                nextStepWizard.removeAttr('disabled').trigger('click');
            }
        }


        if (curStepBtn == "step-2") {

            var isCategoryValid = validateCategory();



            if (!isCategoryValid) {
                isValid = false;
                $("#category").addClass("form-error");
                $("#category").focus();
            }


            var membershipType =  $('#category option:selected').val();


            stepTwoCleared = true;
            if (isValid) {

                nextStepWizard.removeAttr('disabled').trigger('click');
            }

        }




    });


    allPrevBtn.click(function () {


        var curStep = $(this).closest(".setup-content"),
            curStepBtn = curStep.attr("id"),
            nextStepWizard = $('div.setup-panel div a[href="#' + curStepBtn + '"]').parent().prev().children("a")


        nextStepWizard.removeAttr('disabled').trigger('click');

    });

    allPrevBtn2.click(function () {


        var curStep = $(this).closest(".setup-content"),
            curStepBtn = curStep.attr("id"),
            nextStepWizard = $('div.setup-panel div a[href="#' + curStepBtn + '"]').parent().prev().children("a")


        nextStepWizard.removeAttr('disabled').trigger('click');

    });


    $('div.setup-panel div a.btn-primary').trigger('click');

    $('#reg-terms').click(function () {

        if($(this).is(':checked')){
            $('#reg_pay').attr('disabled', false);
        }

    });

    $('#frm-create-fan').bootstrapValidator({

        fields: {
            name: {
                validators: {

                    notEmpty: {
                        message: 'Please Enter your full name'
                    }
                }
            },
            photo: {
                validators: {

                    notEmpty: {
                        message: 'Please capture photo'
                    }
                }
            },
            gender: {
                validators: {
                    notEmpty: {
                        message: 'gender is required and cannot be empty'
                    }

                }
            },
            country: {
                validators: {
                    notEmpty: {
                        message: 'country is required and cannot be empty'
                    }

                }
            },

            organizationTypeId: {
                validators: {
                    notEmpty: {
                        message: 'club is required and cannot be empty'
                    }

                }
            },

            region: {
                validators: {
                    notEmpty: {
                        message: 'region is required and cannot be empty'
                    }

                }
            },

            eduLevel: {
                validators: {
                    notEmpty: {
                        message: 'education level is required and cannot be empty'
                    }

                }
            },

            phoneNumber: {
                validators: {
                    notEmpty: {
                        message: 'Phone number is required and cannot be empty'
                    }

                }
            },


            category: {
                validators: {
                    notEmpty: {
                        message: 'please select category'
                    }

                }
            },

            organizationId: {
                validators: {
                    notEmpty: {
                        message: 'please select club'
                    }

                }
            },
            occupation: {
                validators: {
                    notEmpty: {
                        message: 'please pr occupation'
                    }

                }
            },

            email: {
                validators: {

                    regexp: {
                        regexp: '^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$',
                        message: 'The value is not a valid email address'
                    }
                }
            }

        }
    });


    var contry_ = $('#country option:selected').val();

    if (contry_ == 'ETHIOPIA') {
        $('#eth-address').show();
        $('#eth-address1').show();
        $('#for-address').hide();
        $('#city-address').show();
        $('#eth-region').show();
        $('#eth-subcity').show();
        $('#eth-woreda').show();
        $('#eth-kebele').show();
        $('#eth-houseno').show();
    } else if (contry_ == "") {
        $('#for-address').hide();
        $('#eth-address').hide();
        $('#eth-address1').hide();
        $('#city-address').hide();
        $('#eth-region').hide();
        $('#eth-subcity').hide();
        $('#eth-woreda').hide();
        $('#eth-kebele').hide();
        $('#eth-houseno').hide();
    } else {
        $('#for-address').show();
        $('#eth-address').hide();
        $('#eth-address1').hide();
        $('#city-address').show();
        $('#eth-region').hide();
        $('#eth-subcity').hide();
        $('#eth-woreda').hide();
        $('#eth-kebele').hide();
        $('#eth-houseno').hide();
    }


    $('#country').change(function () {
        var country = $('#country option:selected').val();

        if (country == 'ETHIOPIA') {
            $('#eth-address').show();
            $('#for-address').hide();
            $('#eth-address1').show();
            $('#city-address').show();
            $('#eth-region').show();
            $('#eth-subcity').show();
            $('#eth-woreda').show();
            $('#eth-kebele').show();
            $('#eth-houseno').show();
        } else if (country == "") {
            $('#for-address').hide();
            $('#eth-address').hide();
            $('#eth-address1').hide();
            $('#city-address').hide();
            $('#eth-region').hide();
            $('#eth-subcity').hide();
            $('#eth-woreda').hide();
            $('#eth-kebele').hide();
            $('#eth-houseno').hide();
        } else {
            $('#for-address').show();
            $('#eth-address').hide();
            $('#eth-address1').hide();
            $('#city-address').show();
            $('#eth-region').hide();
            $('#eth-subcity').hide();
            $('#eth-woreda').hide();
            $('#eth-kebele').hide();
            $('#eth-houseno').hide();
        }

    });

});


$('#region').change(function () {
    var regionName = $('#region option:selected').val();

    $.ajax({
        type: 'GET',
        url: contextRoot + "getSubcityByRegion",
        data: {
            'regionName': regionName
        },

        success: function (result) {

            console.log(JSON.stringify(result));
            var $field = $('#subcity');

            $field.empty();
            $field.append(new Option("please select sub city", ""));

            $.each(result, function (id, value) {
                $field.append('<option value=' + id + '>' + value + '</option>');

            });
        }
    });
});

var validFirstName = function () {
    var name_regex = /^[a-zA-Z ]{2,30}$/i;
    var name = $("#name").val();

    if (name == "") {
        return false;
    }
    if (!name_regex.test(name)) {
        return false;
    } else {
        $("#name").closest('div').addClass('has-success');
        return true;
    }
};


var validateGender = function () {

    var gender = $('#gender option:selected').val();

    if (gender == "") {
        return false;
    }

    return true;
};


var validatePhoneNumber = function () {

    var phoneNumber = $("#phoneNumber").val();
    if (phoneNumber == "") {
        return false;
    }
    return true;
};


var validatePhoto = function () {

    var photo = $("#photo").val();

    if (photo == "") {
        return false;
    } else {
        $('#take-pic').hide();
        return true;
    }
};


var validateEducation = function () {

    var eduLevel = $("#eduLevel").val();

    if (eduLevel == "") {
        return false;
    } else {
        return true;
    }
};

var validateOccupation = function () {

    var occupation = $("#occupation").val();

    if (occupation == "") {
        return false;
    } else {
        return true;
    }
};


var validateCountry = function () {

    var country = $('#country option:selected').val();

    if (country == "") {
        return false;
    } else {
        return true;
    }
};


var validateRegion = function () {

    var country = $('#country option:selected').val();
    var region = $('#region option:selected').val();
    ;
    if (country == "ETHIOPIA") {
        if (region == "") {
            return false;
        } else {
            return true;
        }
    }
};


var validateSubcity = function () {

    var country = $('#country option:selected').val();
    var subcity = $('#subcity option:selected').val();
    ;
    if (country == "ETHIOPIA") {
        if (subcity == "") {
            return false;
        } else {
            return true;
        }
    }
};

var validatekebele = function () {

    var country = $('#country option:selected').val();
    var kebele = $('#kebele').val();

    if (country == "ETHIOPIA") {
        if (kebele == "") {
            return false;
        } else {
            return true;
        }
    }
};

var validateHouseNo = function () {

    var country = $('#country option:selected').val();
    var houseNO = $('#houseNumber').val();
    ;
    if (country == "ETHIOPIA") {
        if (houseNO == "") {
            return false;
        } else {
            return true;
        }
    }
};


var validateState = function () {

    var country = $('#country option:selected').val();

    var state = $('#state').val();

    if (country != "ETHIOPIA") {

        if (state == "") {
            return false;
        } else {
            return true;
        }
    } else {
        return true;
    }
};


var validateCategory = function () {

    var category = $('#category option:selected').val();


    if (category == "") {
        return false;
    } else {
        return true;
    }

};


var validateMonth = function () {

    var month = $('#month option:selected').val();


    if (month == "") {
        return false;
    } else {
        return true;
    }

};
var validateCity = function () {

    var country = $('#country option:selected').val();
    var city = $('#city').val();
    ;
    if (country != "ETHIOPIA") {
        if (city == "") {
            return false;
        } else {
            return true;
        }
    } else {
        return true;
    }
};


var validateEmail = function () {
    var email_regex = /^[a-zA-Z0-9._-]+@+[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
    var email = $("#email").val();
    if (email != "") {
        if (!email_regex.test(email)) {
            $("#email").closest('div').addClass("has-error");
            return false;
        } else {
            $("#email").closest('div').addClass('has-success');
            return true;
        }

    }
    {
        return true;
    }
};