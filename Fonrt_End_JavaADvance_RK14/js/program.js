$(function() {
  $(".header").load("header.html");

  //ktra login, nếu có thì load home.html, ko thì load login.html
  $(".footer").load("footer.html");
  if (localStorage.getItem("userLogin")) {
      $(".main").load("home.html");
  } else {
      $(".main").load("login.html");
  }

});

function clickNavLogin() {

  if (localStorage.getItem("userLogin")) {
      $(".main").load("home.html");
  } else {
      $(".main").load("login.html");
  }

}

function clickNavHome() {
  if (localStorage.getItem("userLogin")) {
      $(".main").load("home.html");
  } else {
      $(".main").load("login.html");
  }
}


function login() {
  var uname = document.getElementById("uname").value;
  var password = document.getElementById("psw").value;
  $.ajax({
      url: 'http://localhost:8080/api/v1/users?username=' + uname,
      type: 'GET',
      contentType: "application/json", // type of body (json, xml, text)
      success: function(data, textStatus, xhr) {

          if (textStatus == "error") {
              alert("Error when loading data");
              return;
          }
          if(data == null ){
            document.getElementById("msuname").innerHTML= "username này không tồn tại . Mời bạn nhập lại username !";
          }
          if (data && data.id) {
              //đã lấy được user trả về trong (data)
              if (data.password == password) {
                  alert("Đăng nhập thành công !");
                 
                  localStorage.setItem("userLogin", JSON.stringify(data));
              
                  $(".main").load("home.html");
              } else {
                  document.getElementById("mspsw").innerHTML= "Password sai . Mời bạn nhập lại password !";
              }

          } 

      },
      error(jqXHR, textStatus, errorThrown) {
          console.log(jqXHR);
          console.log(textStatus);
          console.log(errorThrown);
      }
  });

}

function logout() {
  //localStorage.clear();
  localStorage.removeItem("userLogin");
  $(".main").load("login.html");
}

function clickNavUserDetail() {
  if (localStorage.getItem("userLogin")) {
      // về nhà tự tạo lại userdetail.html để hiển thị dữ liệu user đã login
      $(".main").load("userDetail.html", function(data, statusTxt, xhr) {
          showAccount();
      });

  } else {
      $(".main").load("login.html");
  }
}

function clickNavViewListGroups() {
  if (localStorage.getItem("userLogin")) {
    $(".main").load("viewlistgroups.html",function(){
      rowCount();
  


})
  }
  else{
    $(".main").load("login.html");
  }
}
var group = [];

function getListGroup(search, page, pageSize, sortType, field) {
  if (!search) search = "";
  if (!page) page = 0;
  if (!pageSize) pageSize = 5;
  if (!sortType) sortType = "ASC";
  if (!field) field = "id";
  // call API from server    
  $.ajax({
    url: 'http://localhost:8080/api/v1/groups?search=' +
      search + "&page=" + page + "&pageSize=" + pageSize +
      "&sortType=" + sortType + "&field=" + field,
    type: 'GET',
    //data: JSON.stringify(employee), // body
    contentType: "application/json", // type of body (json, xml, text)
    // dataType: 'json', // datatype return
    success: function (data, textStatus, xhr) {
      group = [];
      if (textStatus == "error") {
        // TODO
        alert("Error when loading data");
        return;
      }
      // success
      parseData(data);
      fillGroupToTable();
    },
    error(jqXHR, textStatus, errorThrown) {
      console.log(jqXHR);
      console.log(textStatus);
      console.log(errorThrown);
    }
  });
}

function parseData(data) {
  group = data;

}

function fillGroupToTable() {
  group.forEach(function (item) {
    $('tbody').append(
      '<tr>' +
      '<td style="text-align: center;">' +
      '<input  type="checkbox" name="deleteGroup" value="' + item.id + '"></td>' +
      '<td>' + item.id + '</td>' +
      '<td>' + item.name + '</td>' +
      '<td>' + item.member + '</td>' +
      '<td>' + item.user.userName + '</td>' +
      '<td>' + item.createDate + '</td>' +

      '<td>' +
      '<a class="edit" title="Edit" data-toggle="tooltip" onclick="openUpdateModal(' + item.id + ')"><i class="material-icons">&#xE254;</i></a>' +
      '<a class="delete" title="Delete" data-toggle="tooltip" onClick="openConfirmDelete(' + item.id + ')"><i class="material-icons">&#xE872;</i></a>' +
      '</td>' +
      '</tr>')
  });
}

function buildTable() {
 rowCount();

}




function sortIdDESC() {
  sortType = "DESC";
  $('tbody').empty();
  getListGroup(sortType);

}


function openModal() {
  $('#myModal').modal('show');
}

function hideModal() {
  $('#myModal').modal('hide');
}



function resetFormAdd() {
  document.getElementById("groupNameAdd").value = "";
  document.getElementById("totalMember").value = "";

}

function openModalAdd() {
  $('#myModalAdd').modal('show');
}

function hideModalAdd() {
  $('#myModalAdd').modal('hide');
}

function openAddModalAdd() {
  resetFormAdd();
  openModalAdd();
}
var totalMember =1
 ;
var createDate ;
function openUpdateModal(id) {
  openModal();
  var index = group.findIndex(x => x.id == id);

  // fill data
  document.getElementById("id1").value= group[index].id;
  document.getElementById("groupName").value = group[index].name;
  //  totalMember = group[index].member;
//   // //  createDate = group[index]
 
 }

function updateGroup() {
  var id = document.getElementById("id1").value;
  var newGroupName = document.getElementById("groupName").value;
 
  // TODO validate
  
  
  //  var  group1 = {
  //    id: id,
  //    name: newGroupName,
  //    member: totalMember,
  //   //  createDate: createDate,
  //    user: JSON.parse(localStorage.getItem("userLogin"))
     
  //  }

  $.ajax({
    url: 'http://localhost:8080/api/v1/groups/' + id + "?newGroupName="+ newGroupName,
    type: 'PUT',
    // data: JSON.stringify(group1),
    contentType: "application/json",
    success: function (result) {
      // success
      hideModal();
      showSuccessAlert();
      buildTable();
    },
    error(jqXHR, textStatus, errorThrown) {

      console.log(jqXHR);
      console.log(textStatus);
      console.log(errorThrown);

    }
  });
}

function addGroup() {

  // get data
  var name = document.getElementById("groupNameAdd").value;
  var totalMember = document.getElementById("totalMember").value;
  
  var group = {
    name: name,
    member: totalMember,
    user: JSON.parse(localStorage.getItem("userLogin"))
  };

  $.ajax({
    url: 'http://localhost:8080/api/v1/groups',
    type: 'POST',
    data: JSON.stringify(group), // body
    contentType: "application/json", // type of body (json, xml, text)
    // dataType: 'json', // datatype return
    success: function (data, textStatus, xhr) {
      hideModalAdd();
      showSuccessAlert();
      buildTable();
    },
    error(jqXHR, textStatus, errorThrown) {
      console.log(jqXHR);
      console.log(textStatus);
      console.log(errorThrown);

    }
  });
}

function saveEdit() {
  

   updateGroup();
  
}
function saveAdd() {
  

  addGroup();


}

function openConfirmDelete(id) {
  // get index from employee's id
  var index = group.findIndex(x => x.id == id);
  var name = group[index].name;

  var result = confirm("Want to delete group " + name + "?");
  if (result) {
    deleteGroup(id);
  }
}

function deleteGroup(id) {
  // TODO validate
  $.ajax({
    url: 'http://localhost:8080/api/v1/groups/' + id,
    type: 'DELETE',
    success: function (result) {
      // success
      showSuccessAlert();
      rowCount();
    },
    error(jqXHR, textStatus, errorThrown) {
      console.log(jqXHR);
      console.log(textStatus);
      console.log(errorThrown);
    }
  });
}

function deleteMultiGroups() {
  var lsGroup =[];
  // lấy ra danh sách groups các tag input có name = deleteGroup
  var listGroups = document.getElementsByName("deleteGroup");
  // ktra các phần tử thuộc listGroups xem có ptu nào đã được checked = true

  listGroups.forEach(element => {
      if (element.checked) {
          lsGroup.push(element.value);
      }
  });
  if (lsGroup.length > 0) {
      var result = confirm("Want to delete groups " + lsGroup + "?");
      if (result) {
          // người dùng chọn ok -> tiến hành xóa
          $.ajax({
              url: 'http://localhost:8080/api/v1/groups/deleteMulti',
              type: 'DELETE',
              data: JSON.stringify(lsGroup), // body
              contentType: "application/json", // type of body (json, xml, text)
              // dataType: 'json', // datatype return
              success: function(data, textStatus, xhr) {
                  hideModal();
                  showSuccessAlert();
                  buildTable();
              },
              error(jqXHR, textStatus, errorThrown) {
                  console.log(jqXHR);
                  console.log(textStatus);
                  console.log(errorThrown);
              }
          });
      }
  } else {
      alert("Bạn cần chọn groups trước khi xóa !")
  }

}
let search = "";
function rowCount() {
  $('tbody').empty();
  $('#pagination').empty();
  $.ajax({
      url: 'http://localhost:8080/api/v1/groups?search='+search,
      type: 'GET',
      contentType: "application/json", // type of body (json, xml, text)
      success: function(data, textStatus, xhr) {
          if (data && data.length > 0) {
              // lấy ra được số page = numberOfPage
              var numberOfPage = Math.ceil(data.length / 5);
              console.log(numberOfPage);
              // hàm for có nhiệm vụ : thêm vào tag id=pagination các tag <a> con
              // mặc định page 1(index == 1) class="active", các page khác class="noactive"
              for (let index = 1; index <= numberOfPage; index++) {
                  if (index == 1) {
                      $('#pagination').append(
                          '<a href="#" id="page' + index + '" onclick="pagination('+ index + ',' + numberOfPage + ')" class="active">' + index + '</a>');
                  } else {
                      $('#pagination').append(
                          '<a href="#" id="page' + index + '" onclick="pagination(' + index + ',' + numberOfPage + ')" class="noactive">' + index + '</a>');
                  }
                  
              }
          }
          getListGroup(search, 0, 5);
      },
      error(jqXHR, textStatus, errorThrown) {
          console.log(jqXHR);
          console.log(textStatus);
          console.log(errorThrown);
      }
  });
}
// nhiệm vụ : khi click vào page nào thì page đấy có class là active
// các page thì có class=noactive
function pagination(index, numberOfPage) {
  console.log(index)
  for (let index2 = 1; index2 <= numberOfPage; index2++) {
      let page = 'page' + index2;
      console.log(page)
      if (index == index2) {
          document.getElementById(page).className = "active";
      } else { 
          document.getElementById(page).className = "noactive";
      }
  }
  $('tbody').empty();
  getListGroup(search, (index - 1), 5);
}
function searchNameGroup() {
  search = document.getElementById("search").value;
 rowCount();

}



function openModalRegis() {
  $('#myModalregis').modal('show');
}

function hideModalRegis() {
  $('#myModalregis').modal('hide');
}
 function Registration(){
  openModalRegis();
}
var user = []
function addUser() {

  // get data
  var id = document.getElementById("id").value;
  var userName = document.getElementById("UserName").value;
  var email = document.getElementById("Email").value;
  var password = document.getElementById("Password").value;
  var fisrtName = document.getElementById("FisrtName").value;
  var lastName = document.getElementById("LastName").value;
  var phone = document.getElementById("Phone").value;
  var user = {
    id: id,
    userName: userName,
    email: email,
    password: password,
    fisrtName:fisrtName,
    lastName: lastName,
    phone: phone
  };

  $.ajax({
    url: 'http://localhost:8080/api/v1/users',
    type: 'POST',
    data: JSON.stringify(user), // body
    contentType: "application/json", // type of body (json, xml, text)
    // dataType: 'json', // datatype return
    success: function (data, textStatus, xhr) {
      hideModalRegis()
      showSuccessAlert();
      window.alert("Tạo tài khoản thành công")
      
    },
    error(jqXHR, textStatus, errorThrown) {
      console.log(jqXHR);
      console.log(textStatus);
      console.log(errorThrown);

    }
  });
} 
 function saveregis(){
   addUser();
   
}

function showAccount(){
  document.getElementById("userName").innerHTML ="User Name: "+ JSON.parse(localStorage.getItem("userLogin")).userName;
  document.getElementById("emailUser").innerHTML = "Email: " + JSON.parse(localStorage.getItem("userLogin")).email;
  document.getElementById("fristName").innerHTML = "Frist Name: " + JSON.parse(localStorage.getItem("userLogin")).fisrtName;
  document.getElementById("lastName").innerHTML = "Last Name: " + JSON.parse(localStorage.getItem("userLogin")).lastName;
  document.getElementById("phone").innerHTML = "Phone: " + JSON.parse(localStorage.getItem("userLogin")).phone;
}
function showSuccessAlert() {
  $("#success-alert").fadeTo(800, 500).slideUp(500, function () {
    $("#success-alert").slideUp(500);
  });
}