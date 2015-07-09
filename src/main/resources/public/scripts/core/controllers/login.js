(function(){

    angular.module('rest-contacts-book').controller('LoginPageCtrl', ctrl);

    ctrl.$inject = [
        '$scope'
        , 'AuthService'
        , '$state'
        , '$log'
    ];

    function ctrl($scope, AuthService, $state, $log) {

        $scope.form = {
            bank: 'bveb'
        };

        $scope.wrongCreds = false;

        $scope.submit = function() {
            $log.debug('LoginPageCtrl#login');
            AuthService.login($scope.form.username, $scope.form.password).then(
                function(){
                    $state.go('root.secured.home');
                }
                , function(){
                    $scope.wrongCreds = true;
                }
            );
        };

        $scope.reset = function() {
            $scope.wrongCreds = false;
            $scope.form = {
                bank: 'bveb'
            };
        };





    }

})();