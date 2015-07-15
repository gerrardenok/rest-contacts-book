(function() {

    angular.module('rest-contacts-book')
        .factory('ContactResource', res);

    res.$inject = ['$resource', '$http', '$q'];

    function res($resource, $http) {
        var ModelClass = $resource('/rest/api/contact/:id', {id: '@id'}, {
            update: { method: 'PUT', url: '/rest/api/contact/:id' }
        });

        ModelClass.page = function() {
            return $http.get('/rest/api/contact/search').then(
                function(response) {
                    var out = [];
                    if(!response.data.content) {
                        return out;
                    } else {
                        angular.forEach(response.data.content, function(e) {
                            out.push(new ModelClass(e));
                        })
                    }
                    return out;
                },
                function(e) {
                    return $q.reject(e);
                }
            );
        };

        return ModelClass;
    }

})();
