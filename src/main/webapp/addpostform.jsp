<%--
  Created by IntelliJ IDEA.
  User: haeun
  Date: 2022/11/28
  Time: 1:15 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="./contents/css/navbar.css" rel="stylesheet">
    <title>Form Example</title>
    <style>
        .form-label{
            font-weight: 700 !important;
        }
    </style>
</head>
<body>
<jsp:include page="navbar.jsp" />
<div class="container">
    <div class="text-center my-3"><h3>Registration Form</h3></div>
    <div class="py-5">
        <form action="addpost.jsp" enctype = "multipart/form-data" method="post">
            <div class="mb-2">
                <label class="form-label" for="form6Example1">Profile Image</label>
                <div class="row">
                    <div class="col-sm d-flex justify-content-between">
                        <div class="form-outline">
                            <img src="./contents/images/img.png" alt="..." width="100px" height="100px" class="img-thumbnail" id="profile">
                        </div>
                    </div>
                    <div class="col-sm d-flex flex-column justify-content-between">
                        <input type="file" class="form-control" id="customFile" name="photo" onchange="setThumbnail(this)"  accept="image/*,.pdf"/>
                    </div>
                    <div class="col-sm d-flex flex-column justify-content-between">
                    <!-- User Type input -->
                        <label class="form-label" for="exampleFormControlSelect1">User Type</label>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="userType" id="exampleRadios1" value="Admin" checked>
                            <label class="form-check-label" for="exampleRadios1">
                                Admin
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="userType" id="exampleRadios2" value="User">
                            <label class="form-check-label" for="exampleRadios2">
                                User
                            </label>
                        </div>
                    </div>

                </div>
            </div>

            <!-- 2 column grid layout with text inputs for the first and last names -->
            <div class="row mb-2">
                <div class="col">
                    <div class="form-outline">
                        <label class="form-label" for="form6Example1">First name</label>
                        <input type="text" id="form6Example1" class="form-control"  name="firstName" placeholder="Gildong" required/>
                    </div>
                </div>
                <div class="col">
                    <div class="form-outline">
                        <label class="form-label" for="form6Example2">Last name</label>
                        <input type="text" id="form6Example2" class="form-control" name="lastName"  placeholder="Hong" required/>
                    </div>
                </div>
            </div>

            <!-- Radio input -->
            <div class="row mb-2">
                <div class="col">
                    <div class="form-outline form-group">
                        <label class="form-label" for="exampleFormControlSelect1">Gender</label>
                        <select class="form-control" id="exampleFormControlSelect1" name="gender" required>
                            <option>Male</option>
                            <option>Female</option>
                        </select>
                    </div>
                </div>
                <div class="col">
                    <div class="form-outline">
                        <label class="form-label" for="form6Example3">Birthday</label>
                        <input type="date" id="form6Example3" class="form-control" name="birthday" required/>
                    </div>
                </div>

            </div>
            <!-- PhoneNumber input -->
            <div class="form-outline mb-4">
                <label class="form-label" for="form6Example6">PhoneNumber</label>
                <input type="number" id="form6Example6" class="form-control" name="phone" placeholder="010********" required/>
            </div>

            <!-- Email input -->
            <div class="form-outline mb-4">
                <label class="form-label" for="form6Example5">Email</label>
                <input type="email" id="form6Example5" class="form-control" name="email" placeholder="example@handong.ac.kr" required/>
            </div>

            <!-- Message input -->
            <div class="form-outline mb-4">
                <label class="form-label" for="form6Example7">Additional information</label>
                <textarea class="form-control" id="form6Example7" rows="4" name="information"></textarea>
            </div>

            <!-- Submit button -->
            <div class="d-flex justify-content-between mb-2">
                <div><button type="submit" value="Add Post" class="btn btn-primary">Register</button></div>
            </div>
        </form>
    </div>
</div>
<script>

    function setThumbnail(input){

        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                document.getElementById('profile').src = e.target.result;
            };
            reader.readAsDataURL(input.files[0]);
        } else {
            document.getElementById('profile').src = "./contents/images/img.png";
        }
    }
</script>
<jsp:include page="footer.jsp" />
</body>
</html>
