(function() {

    angular.module('rest-contacts-book')
        .factory('ContactResource', res);

    res.$inject = ['$resource', '$http', '$q'];

    function res($resource, $http) {
        var ModelClass = $resource('/rest/api/contact/:id', {id: '@id'}, {
            update: { method: 'PUT', url: '/rest/api/contact/:id' }
        });

        ModelClass.page = function(params) {
            return $http(
                {
                    url: '/rest/api/contact/search',
                    method: "GET",
                    params: params || {}
                }
            ).then(
                function(response) {
                    var out = [];
                    if(!response.data.content) {
                        return out;
                    } else {
                        angular.forEach(response.data.content, function(e) {
                            out.push(new ModelClass(e));
                        })
                    }
                    return {
                        items: out,
                        total: response.data.totalElements
                    };
                },
                function(e) {
                    return $q.reject(e);
                }
            );
        };

        return ModelClass;
    }

})();
