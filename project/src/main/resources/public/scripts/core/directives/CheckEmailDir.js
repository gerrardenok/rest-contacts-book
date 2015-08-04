(function(){

    angular.module('rest-contacts-book').directive('checkEmailDir', dir);

    dir.$inject = [
        '$http'
    ];

    function dir($http) {
        return {
            require: 'ngModel',
            link: function (scope, elem, attrs, ctrl) {
                elem.on('blur', function (evt) {
                    scope.$apply(function () {
                        var val = elem.val();
                        var EMAIL_REGEXP = /^([0-9a-zA-Z]([-\.\w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-\w]*[0-9a-zA-Z]\.)+[a-zA-Z]{2,9})$/;
                        if (!val || EMAIL_REGEXP.test(val)) {
                            ctrl.$setValidity('email', true);
                            $http({
                                url: '/rest/api/auth/checkEmail',
                                method: 'GET',
                                params: {email: val}
                            }).then(
                                function(data) {
                                    ctrl.$setValidity('unique', true);
                                },
                                function(error) {
                                    ctrl.$setValidity('unique', false);
                                }
                            );
                        } else {
                            ctrl.$setValidity('email', false);
                        }
                    });
                });
            }
        }
    }

})();