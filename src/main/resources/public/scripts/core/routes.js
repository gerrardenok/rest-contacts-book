(function(){

    angular.module('rest-contacts-book').config(cfg);

    cfg.$inject = [
        '$stateProvider'
        , '$urlRouterProvider'
    ];

    function cfg($stateProvider, $urlRouterProvider){

        $urlRouterProvider.otherwise('/not_found');

        $urlRouterProvider
            .when('/', '/home')
            .when('', '/login');

        // ------------------------------------- //
        // --------------Root------------------- //
        // ------------------------------------- //

        $stateProvider.state('root', {
            abstract: true
        });


        // ------------------------------------- //
        // -----------Public routes------------- //
        // ------------------------------------- //

        $stateProvider.state('root.public', {
            abstract: true
        });

        $stateProvider.state('root.public.notFound', {
            url: '/not_found'
            , views: {
                'root@': {
                    templateUrl: '/views/pages/notFound.html'
                }
            }
        });

        $stateProvider.state('root.public.error', {
            url: '/error'
            , views: {
                'root@': {
                    templateUrl: '/views/pages/error.html'
                }
            }
        });

        $stateProvider.state('root.public.login', {
            url: '/login'
            , views: {
                'root@': {
                    controller: 'LoginPageCtrl', templateUrl: '/views/pages/login.html'
                }
            }
        });

        // ------------------------------------- //
        // -----------Secured routes------------ //
        // ------------------------------------- //

        $stateProvider.state('root.secured', {
            abstract: true
            , resolve: {
                'loggedIn': ['AuthService', function(srv){
                    return srv.checkAuth();
                }]
            }
            , views: {
                'root@': {
                    templateUrl: '/views/pages/layout.html'
                }
            }
        });

        $stateProvider.state('root.secured.home', {
            url: '/home'
            , views: {
                'content@root.secured': {
                    templateUrl: '/views/pages/home.html'
                }
            }
        });

    }

})();
