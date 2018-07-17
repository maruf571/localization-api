app.service('languageService',
    function($http) {

    var languageApi = "/api/languages";

    this.findAll = function(query) {
     if(query == undefined)
        query = '';

     return $http.get(languageApi +'/'+ query).then(function(resp){
           return resp.data;
     });
    };

    this.findOne = function(id) {
     return $http.get(languageApi + '/' + id).then(function(resp){
           return resp.data;
     });
    };


    this.submit = function(data) {

        if(data.id){
            //edit
            return $http.put(languageApi, data).then(function(resp){
                return resp.data;
            });
        }else{
            //new
            return $http.post(languageApi, data).then(function(resp){
               return resp.data;
            });
        }
    };

    this.delete = function(id) {
     return $http.delete(languageApi + '/' + id).then(function(resp){
           return resp.data;
     });
    };

});