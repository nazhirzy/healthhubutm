<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>BMI Result</title>
<style>
  body {
    font-family: Arial, sans-serif;
    background: #f6f8fb;
    margin: 0;
    padding: 40px;
  }
  .card {
    max-width: 520px;
    margin: auto;
    background: #fff;
    padding: 24px;
    border-radius: 14px;
    box-shadow: 0 8px 22px rgba(0,0,0,.08);
  }
  h1 {
    text-align: center;
    margin-top: 0;
  }
  .kv {
    display: flex;
    justify-content: space-between;
    margin: .35rem 0;
  }
  .big {
    font-size: 1.6rem;
    font-weight: 700;
  }
  a.btn {
    display: inline-block;
    margin-top: 12px;
    padding: .6rem .8rem;
    border-radius: 10px;
    border: 1px solid #ddd;
    text-decoration: none;
  }
</style>
</head>

<body>
<div class="card">
  <h1>BMI Result</h1>


  <!-- show results  -->
  <div>
    <div class="kv"><span>Name</span><span>${person.name}</span></div>
    <div class="kv"><span>Year of Birth</span><span>${person.yob}</span></div>
    <div class="kv"><span>Age</span><span>${person.age}</span></div>
    <div class="kv"><span>Weight (kg)</span><span>${person.weight}</span></div>
    <div class="kv"><span>Height (m)</span><span>${person.height}</span></div>
    <div class="kv big"><span>BMI</span><span>${person.bmi}</span></div>
    <div class="kv"><span>Category</span><span>${person.category}</span></div>
    <div class="kv"><span>Interests</span><span>${person.interest[0]}</span></div>
    <div class="kv"><span>Interests</span><span>${person.interest[1]}</span></div>
    <div class="kv"><span>Interests</span><span>${person.interest[2]}</span></div>


  </div>
</div>
</body>
</html>
