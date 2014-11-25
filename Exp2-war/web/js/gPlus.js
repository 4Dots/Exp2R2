
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

function verAmigos() {

  var uToken = getUserToken;

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