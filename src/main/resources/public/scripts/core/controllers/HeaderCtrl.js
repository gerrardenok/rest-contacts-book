(function(){

    angular.module('rest-contacts-book').controller('HeaderCtrl', ctrl);

    ctrl.$inject = [
        '$scope'
        , '$interval'
        , 'AuthService'
        , '$state'
        , '$log'
    ];

    function ctrl($scope, $interval, AuthService, $state, $log) {

        $scope.currentDate = new Date();

        $interval(function(){
            $scope.currentDate = new Date();
        }, 100);

        $scope.logout = function(){
            $log.debug('HeaderCtrl#login');
            AuthService.logout().then(function(){
                $state.go('root.public.login');
            })
        };

    }

})();