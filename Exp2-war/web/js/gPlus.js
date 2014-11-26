
function signinCallback(authResult) {
  if (authResult['status']['signed_in']) {
    // Update the app to reflect a signed in user
    // Hide the sign-in button now that the user is authorized, for example:
    //document.getElementById('signinButton').setAttribute('style', 'display: none');
    
    gapi.client.load('plus','v1', function(){
     var request = gapi.client.plus.people.get({
       'userId': 'me'
     });
     request.execute(function(resp) {
       console.log('Retrieved profile for:' + resp.displayName);
       document.getElementById('nombreUser').innerHTML = resp.displayName;
     });
   });

  document.getElementById('fbThing').setAttribute('style', 'display: none');  
  document.getElementById('gPlusYay').setAttribute('style', 'display: inline');  
  
  //window.location.href = "./index.html";    

  } else {
    // Update the app to reflect a signed out user
    // Possible error values:
    //   "user_signed_out" - User is signed-out
    //   "access_denied" - User denied access to your app
    //   "immediate_failed" - Could not automatically log in the user
    console.log('Sign-in state: ' + authResult['error']);
  }
}

function getMe() {
  gapi.client.load('plus','v1', function(){
     var request = gapi.client.plus.people.get({
       'userId': 'me'
     });
     request.execute(function(resp) {
       console.log('Retrieved profile for:' + resp.displayName);
       document.getElementById('nombreUser').innerHTML = resp.displayName;
     });
   });
}

function showFriends(result)
{  
  //document.getElementById('hideThis').setAttribute('style','display:none');
  var request = gapi.client.plus.people.list(
  {
    'userId': 'me',
    'collection' : 'visible'
  });
  request.execute(function (resp)
  {
    var email = '';
    var str = "";
    var collapses = "<ul id=\"ffs\">";

    for(var i = 0; i < resp.items.length; i++)
    {
      //if(resp[i]['type'] == 'account')
      //{
        //email = resp.items[i]['emails'][i]['value'];
        //str += "Name:" + resp.items[i]['displayName'] + "<br>";
        //str += "Image:" + resp.items[i]['image']['url'] + "<br>";
        //str += "<img src='" + resp.items[i]['image']['url'] + "' /><br>";

        //str += "URL:" + resp.items[i]['url'] + "<br>";
        //str += "Email:" + email + "<br>";
        var name = resp.items[i]['displayName'];
        var pName = name.replace(/ /g,'');

        collapses += "<li><div class=\"panel-group\" id=\"accordion\">        <div class=\"panel panel-default\">        <div class=\"panel-heading\">        <h4 class=\"panel-title\">        <a data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapse"+pName+"\">        "+name+"        </a>        </h4>        </div>        <div id=\"collapse"+pName+"\" class=\"panel-collapse collapse\" style=\"\">        <div class=\"panel-body\" style=\"color:#2c3e50\">        <h5>Detalles</h5>        <img style=\"float:left\" src=\""+resp.items[i]['image']['url']+"\"/>  Perfil: <a target=\"blank\" href=\""+resp.items[i]['url']+"\">"+resp.items[i]['url']+"</a>   <br>   <button class=\"btn-success\" style=\"float:left; margin-left:1.2em\" >Enviar bono</button>        </div>        </form>        </div>        </div>        </div>        </div></li>"

      //}
      //console.log(str);
    }
    collapses += "</ul>";
    document.getElementById('hideThis').innerHTML = collapses;
  });
}



 



function verAmigos() {

  var uToken = getUserToken();

  // var userID;

  // gapi.client.load('plus','v1', function(){
  //    var request = gapi.client.plus.people.get({
  //      'userId': 'me'
  //    });
  //    request.execute(function(resp) {
  //      console.log('Retrieved profile for:' + resp.displayName);
  //      userID = resp.id;
  //      //document.getElementById('nombreUser').innerHTML = resp.displayName;
  //    });
  //  });

  // gapi.client.load('plus','v1', function(){
  //    var request = gapi.client.plus.people.list({
  //      'userId': 'me',
  //      'collection': 'visible'
  //    });
  //    request.execute(function(resp) {
  //      console.log('asdasd:' + resp.items[0].displayName);
  //      var friends = resp.items;
  //      var arrayFriends = [];
  //      arrayFriends.push(resp);
  //      console.log(friends)
  //      document.location.href = "./likes?Likes=&gPlus=true&id="+userID+"&ppl="+arrayFriends;
       //alert(friends[0].displayName);
      // $.post("./likes",
      // {
      //   Likes:"",
      //   gPlus:"true",
      //   id: resp.id,
      //   ppl: friends
      // },
      // function(data,status){
      //   alert("Data: " + data + "\nStatus: " + status);
      // });
     //});
   //});


  // gapi.client.load('plus','v1', function(){
  //    var request = gapi.client.plus.people.get({
  //      'userId': 'me'
  //    });
  //    request.execute(function(resp) {
  //      console.log('id:' + resp.id);
  //      //Llamado al servlet
  //     //document.location.href = "./likes?Likes=&gPlus=true&id="+resp.id;

  //     $.post("./likes",
  //     {
  //       Likes:"",
  //       gPlus:"true",
  //       id: resp.id
  //       ppl: resp.
  //     },
  //     function(data,status){
  //       alert("Data: " + data + "\nStatus: " + status);
  //     });

  //    });
  //  });
}

function getUserToken(){

  var Token = gapi.auth.getToken('token',false);
  console.log(Token.access_token);

  return Token.acces_token;
}