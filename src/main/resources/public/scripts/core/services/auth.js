(function(){

    angular.module('rest-contacts-book').factory('AuthService', srv);

    srv.$inject = [
        '$http'
        , '$rootScope'
        , '$q'
        , '$state'
        , '$log'
    ];

    function srv($http, $rootScope, $q, $state, $log){

        var service = {};

        service.login = function(userName, password){
            $log.debug('AuthService#login');
            return $http.get(
                '/rest/api/user/current'
                , {headers : httpBasicHeaders(userName, password)}
            ).then(
                checkAuthSuccess
                , function(e) {
                    $rootScope.GLOBALS.authenticated = false;
                    return $q.reject(e)
                }
            );
        };

        service.logout = function(){
            $log.debug('AuthService#login');
            return $http.post('/logout', {}).then(function(){
                $rootScope.GLOBALS.authenticated = false;
                $rootScope.GLOBALS.currentUser = null;
            });
        };

        service.checkAuth = function(){
            return $http.get('/rest/api/user/current').then(
                checkAuthSuccess
                , function(e) {
                    $rootScope.GLOBALS.authenticated = false;
                    $state.go('root.public.login');
                    return $q.reject(e);
                }
            );
        };

        service.checkAdmin = function() {
            var defer = $q.defer();
            if($rootScope.GLOBALS.currentUser.isAdmin) {
                defer.resolve(true);
            } else {
                defer.reject(false);
            }
            return defer.promise;
        };

        function httpBasicHeaders(userName, password){
            return {authorization: "Basic "+btoa(userName+":"+password)}
        }

        function checkAuthSuccess(response){
            $rootScope.GLOBALS.authenticated = true;
            $rootScope.GLOBALS.currentUser = response.data;
            $rootScope.GLOBALS.currentCard = $rootScope.GLOBALS.currentUser.cards[0];
            return response.data;
        }

        return service;

    }

})();
