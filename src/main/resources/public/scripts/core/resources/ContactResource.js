(function() {

    angular.module('rest-contacts-book')
        .factory('ContactResource', res);

    res.$inject = ['$resource', '$http'];

    function res($resource, $http) {
        var ModelClass = $resource('/rest/api/contact', {id: '@id'});

        ModelClass.page = function() {
            return $http.get('/rest/api/contact/search');
        };

        return ModelClass;
    }

})();
