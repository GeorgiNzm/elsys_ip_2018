<%--
  Created by IntelliJ IDEA.
  User: milko.mitropolitsky
  Date: 11/29/17
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <title>RESTFul Application</title>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
      <script type="text/javascript">
          $(function () {
              listWeapons();
          });

          function listWeapons() {
              $.get("/api/weapons", function (data) {
                  buildTable(data);
              });
          }

          function buildTable(data) {
              var tableRow = "<tr>"
              var table = ""

              for(var index = 0; index < data.length; index++) {
                  tableRow += ("<td>" + data[index].id + "</td>")
                  tableRow += ("<td>" + data[index].type + "</td>")
                  tableRow += ("<td>" + data[index].category + "</td>")
                  tableRow += ("<td>" + data[index].brand + "</td>")
                  tableRow += ("<td>" + data[index].model + "</td>")
                  tableRow += ("<td>" + data[index].caliber + "</td>")
                  tableRow += ("<td>" + data[index].barrelLength + "</td>")
                  tableRow += ("<td>" + data[index].capacity + "</td>")
                  tableRow += ("<td>" + data[index].frameFinish + "</td>")
                  tableRow += ("<td>" + data[index].material + "</td>")
                  tableRow += ("<td>" + data[index].weight + "</td>")
                  tableRow += ("<td><button onclick='editShow(" + data[index].id + ")'>Edit</button> </td>")
                  tableRow += ("<td><button onclick='deleteWeapon(" + data[index].id + ")'>Delete</button></td></tr>")
                  table += tableRow
              }



              $("#table-body").html(table)
          }

          function createWeapon() {
              var weapon = {}

              $(".create").each(function () {
                  weapon[$(this).attr("name")] = $(this).val()
              });

              $.ajax({
                  url: '/api/weapons',
                  type: 'POST',
                  data: JSON.stringify(weapon),
                  headers: {"Content-Type" : "application/json"},
                  success: function () {
                      listWeapons();
                  }
              });
          }

          function buildPattern() {
              var pattern = ""

              $(".create").each(function () {
                  pattern = pattern + $(this).attr("name") + "=" + $(this).val() + "&"
              });

              return pattern
          }

          function searchWeapon() {
              $.ajax({
                  url: '/api/weapons?' + buildPattern(),
                  type: 'GET',
                  success: function (data) {
                      listWeapons(data);
                  }
              })
          }

          function editWeapon() {
              var weapon = {}

              $(".edit").each(function () {

                  if ($(this).val() != "") {
                      weapon[$(this).attr("name")] = $(this).val()
                  }
              });

              $.ajax({
                  url: '/api/weapons/' + $("#pk").val(id),
                  type: 'PUT',
                  data: JSON.stringify(query),
                  success: function () {
                      $("#edit-input").hide()
                      listWeapons();
                  }
              });
          }

          function editShow(id) {
              $("#edit-input").show()
              $("#pk").val(id)
          }

          function deleteWeapon(id) {
              $.ajax({
                  url: '/api/weapons/' + id,
                  type: 'DELETE',
                  success: function () {
                      listWeapons();
                  }

              })
          }

          function addUploaded() {
              $("#upload-form").submit(function () {
                 listWeapons();
              });
          }
      </script>
  </head>
  <body>

   <h1>Welcome to the Application...</h1>

   <div id="create-form">
       Id: <input class="create" type="text" name="id" value=""><br>
       Type: <input class="create" type="text" name="type" value=""><br>
       Category: <input class="create" type="text" name="category" value=""><br>
       Brand: <input class="create" type="text" name="brand" value=""><br>
       Model: <input class="create" type="text" name="model" value=""><br>
       Caliber: <input class="create" type="text" name="caliber" value=""><br>
       Barrel Length: <input class="create" type="text" name="barrelLength" value=""><br>
       Capacity: <input class="create" type="text" name="capacity" value=""><br>
       Frame Finish: <input class="create" type="text" name="frameFinish" value=""><br>
       Material: <input class="create" type="text" name="material" value=""><br>
       Weight: <input class="create" type="text" name="weight" value=""><br>

       <button onclick="createWeapon()">Create weapon</button>
       <button onclick="searchWeapon()">Search weapon</button>
   </div>

   <br><br>

   <div id="edit-input" hidden>
       <input class="edit" id="pk" type="text" name="id" value="" readonly><br>
       Type: <input class="edit" type="text" name="type" value=""><br>
       Category: <input class="edit" type="text" name="category" value=""><br>
       Brand: <input class="edit" type="text" name="brand" value=""><br>
       Model: <input class="edit" type="text" name="model" value=""><br>
       Caliber: <input class="edit" type="text" name="caliber" value=""><br>
       Barrel Length: <input class="edit" type="text" name="barrelLength" value=""><br>
       Capacity: <input class="edit" type="text" name="capacity" value=""><br>
       Frame Finish: <input class="edit" type="text" name="frameFinish" value=""><br>
       Material: <input class="edit" type="text" name="material" value=""><br>
       Weight: <input class="edit" type="text" name="weight" value=""><br>

        <button onclick="editWeapon()">Edit</button>
   </div>

   <table id="main" cellspacing="12">
       <thead>
        <tr>
            <th>Id</th>
            <th>Type</th>
            <th>Category</th>
            <th>Brand</th>
            <th>Model</th>
            <th>Caliber</th>
            <th>BarrelLength</th>
            <th>Capacity</th>
            <th>FrameFinish</th>
            <th>Material</th>
            <th>Weight</th>
        </tr>
       </thead>

       <tbody id="table-body"></tbody>
   </table>

   <form id="upload-form" action="/api/weapons/upload" enctype="multipart/form-data" method="post">
       File: <input type="file" name="csv"/>
       <input type="submit" value="Upload"/>
   </form>
      <br><br>

   <button onclick="window.location.href='/api/weapons/download'">Download</button>

  </body>
</html>
