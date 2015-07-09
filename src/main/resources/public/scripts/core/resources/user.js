(function() {

    angular.module('rest-contacts-book')
        .factory('UserResource', res);

    res.$inject = ['$resource'];

    function res($resource) {
        var ModelClass = $resource('', {id: '@id'},
            {
                getMe: { method: 'GET', url: '/rest/api/user/current' }
                , query: { method: 'GET', url: '/rest/api/users', isArray: true }
                , get: { method: 'GET', url: '/rest/api/user/:id' }
                , save: { method: 'POST', url: '/rest/api/user' }
                , update: { method: 'PUT', url: '/rest/api/user/:id' }
                , delete: { method: 'DELETE', url: '/rest/api/user/:id' }
            }
        );

        ModelClass.prototype.$isAdmin = function() {
            return this.isAdmin;
        };

        return ModelClass;
    }

})();
