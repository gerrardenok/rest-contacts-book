(function(){

    angular.module('rest-contacts-book').controller('AuthPageCtrl', ctrl);

    ctrl.$inject = [
        '$scope'
        , 'AuthService'
        , '$state'
        , '$log'
    ];

    function ctrl($scope, AuthService, $state, $log) {

        $scope.loginData = {};
        $scope.registrationData = {};
        $scope.wrongCreds = false;


        $scope.submitLogin = function() {
            $log.debug('LoginPageCtrl#login');
            AuthService.login($scope.loginData.email, $scope.loginData.password).then(
                function(){
                    $state.go('root.secured.home');
                }
                , function(){
                    $scope.wrongCreds = true;
                }
            );
        };

        $scope.submitRegistration = function() {
            $log.debug('LoginPageCtrl#registration');
            AuthService.registerUser(
                $scope.registrationData.userName,
                $scope.registrationData.email,
                $scope.registrationData.password
            ).then(function(){
                return AuthService.login($scope.registrationData.email, $scope.registrationData.password).then(
                    function(){
                        $state.go('root.secured.home');
                    }
                )
            });

        };

        // -------------------------------- //
        // -------------------------------- //

        var activeForm = 'login';

        $scope.activateLoginForm = function(){
            activeForm = 'login';
        };

        $scope.isLoginFormActivate = function(){
            return activeForm == 'login';
        };

        $scope.activateRegistrationForm = function(){
            activeForm = 'registration';
        };

        $scope.isRegistrationFormActivate = function(){
            return activeForm == 'registration';
        };

    }


})();