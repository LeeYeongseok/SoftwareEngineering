<!doctype html>
<html>
  <head>
  <meta charset="utf-8">
    <title>HTML</title>
    <style>
      * {
        font-size: 16px;
        font-family: Consolas, sans-serif;
      }
    </style>
  </head>
  <body>
    <form method="post" action="searchRoom-action.php">
      <p><label>날짜 : <input type="text" name="hotelname"></label></p>
      <p><label>위치 : <input type="text" name="location"></label></p>
      <p><label>인원수 : <input type="text" name="maxGuest"></label></p>
      <p><input type="Submit" value="방 찾기"></p>
    </form>
  </body>
</html>
