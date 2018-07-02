app.service('localizationService',
    function($http) {

    var localizationApi = "/api/localizations";

    this.findAll = function(query) {
     if(query == undefined)
        query = '';

     return $http.get(localizationApi +'/'+ query).then(function(resp){
           return resp.data;
     });
    };

    this.findOne = function(id) {
     return $http.get(localizationApi + '/' + id).then(function(resp){
           return resp.data;
     });
    };


    this.save = function(data) {

        if(data.id){
            //edit
            return $http.put(localizationApi, data).then(function(resp){
                return resp.data;
            });
        }else{
            //new
            return $http.post(localizationApi, data).then(function(resp){
               return resp.data;
            });
        }
    };

    this.delete = function(id) {
     return $http.delete(localizationApi + '/' + id).then(function(resp){
           return resp.data;
     });
    };

});