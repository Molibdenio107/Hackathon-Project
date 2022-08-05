$(document).ready(function () {
    $("#addCustomer").on("click",addCustomer);
  });
  
  function addCustomer(){
    $.ajax({
        url: "http://localhost:8080/api/customer",
        type: 'POST',
        Headers: {"Access-Control-Allow-Origin": "*"},
        data: JSON.stringify({
            name: $('#customerName').val(),
            username: $('#customerUserName').val(),
            password: $('#customerPassword').val(),
            email: $('#customeremail').val(),
            phone: $('#customerphone').val(),
            address: $('#customerShippingAddress').val(),
            city: $('#customerCity').val(),
            zipcode: $('#customerzipcode').val(),
            nif: $('#customerNIF').val(),
        }),
        async: true,
        contentType: 'application/json',
        success: "nao",
        error: function (request, status, error) {
            console.log("Something went wrong: ", status, error);
        }
    });
    resetbutton();
  }
  function resetbutton(){
    $("#customerName").val("");
    $("#customerUserName").val("");
    $("#customerPassword").val("");
    $("#customeremail").val("");
    $("#customeremail").val("");
    $("#customerphone").val("");
    $("#customerShippingAddress").val("");
    $("#customerCity").val("");
    $("#customerzipcode").val("");
    $("#customerNIF").val("");
  }