(function() {

    angular.module('rest-contacts-book')
        .factory('UserResource', res);

    res.$inject = ['$resource'];

    function res($resource) {
        var ModelClass = $resource('/rest/api/user', {id: '@id'},
            {
                getMe: { method: 'GET', url: '/rest/api/user/current' }
            }
        );

        ModelClass.prototype.$isAdmin = function() {
            return this.isAdmin;
        };

        return ModelClass;
    }

})();
