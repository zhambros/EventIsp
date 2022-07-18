<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html>
  <html>

  <head>
    <title>Home | My Masjid</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
      integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
      crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
      a,
      a:focus,
      a:hover {
        color: #fff;
      }

      .btn-secondary,
      .btn-secondary:hover,
      .btn-secondary:focus {
        color: #333;
        text-shadow: none;
        background-color: #fff;
        border: .05rem solid #fff;
      }

      html,
      body {
        height: 100%;
        background-image: url("https://images.lifestyleasia.com/wp-content/uploads/sites/7/2022/03/30141713/helia-ziyaee-7nmwjhrxvus-unsplash.jpeg");
        background-size: cover;
      }

      body {
        display: -ms-flexbox;
        display: -webkit-box;
        display: flex;
        -ms-flex-pack: center;
        -webkit-box-pack: center;
        justify-content: center;
        color: #fff;
        text-shadow: 0 .05rem .1rem rgba(0, 0, 0, .5);
        box-shadow: inset 0 0 5rem rgba(0, 0, 0, .5);
      }

      .cover {
        background-color: rgba(0, 0, 0, .8);
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        text-shadow: none;
        color: #fff;
        padding: 2rem 3rem;
      }

      .cover>p {
        font-weight: 400;
      }

      .cover-container {
        max-width: 60rem;
      }

      .masthead {
        margin-bottom: 2rem;
      }

      .masthead-brand {
        margin-bottom: 0;
      }

      .nav-masthead .nav-link {
        padding: .25rem 0;
        font-weight: 700;
        color: rgba(255, 255, 255, .5);
        background-color: transparent;
        border-bottom: .25rem solid transparent;
      }

      .nav-masthead .nav-link:hover,
      .nav-masthead .nav-link:focus {
        border-bottom-color: rgba(255, 255, 255, .25);
      }

      .nav-masthead .nav-link+.nav-link {
        margin-left: 1rem;
      }

      .nav-masthead .active {
        color: #fff;
        border-bottom-color: #fff;
      }

      @media (min-width: 48em) {
        .masthead-brand {
          float: left;
        }

        .nav-masthead {
          float: right;
        }
      }

      .cover .btn-lg {
        padding: .75rem 1.25rem;
        font-weight: 700;
      }

      .mastfoot {
        color: rgba(255, 255, 255, .5);
      }
    </style>
  </head>

  <body>
    <div class="cover-container d-flex h-100 p-3 mx-auto flex-column">
      <header class="masthead mb-auto">
        <div class="inner">
          <h2 class="masthead-brand">My Masjid</h2>
          <nav class="nav nav-masthead justify-content-center">
            <a class="nav-link active" href="#">Home</a>
            <a class="nav-link" href="MosqueManagement/login.jsp">Mosque Management</a>
            <a class="nav-link" href="People/login.jsp">People</a>
          </nav>
        </div>
      </header>

      <main role="main" class="inner cover">
        <h1 class="cover-heading">Welcome</h1>
        <p class="lead">To Home Page Of <b>My Masjid</b> - An Event Management System Made Specially For Mosques!</p>
      </main>

      <footer class="mastfoot mt-auto">
        <div class="inner text-center">
          <p>Made With <i class="fa-solid fa-heart"></i></p>
          </p>
        </div>
      </footer>
    </div>
  </body>

  </html>