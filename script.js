function loginUser(event) {
     event.preventDefault();
     const username = document.getElementById("username").value;
     const password = document.getElementById("password").value;
   
     if (username === "admin" && password === "1234") {
       localStorage.setItem("loggedInUser", username);
       window.location.href = "dashboard.html";
     } else {
       alert("Invalid credentials. Try admin / 1234");
     }
   }
   
   function logout() {
     localStorage.removeItem("loggedInUser");
     window.location.href = "index.html";
   }
   
   function markAttendance(action) {
     const now = new Date();
     const record = {
       date: now.toLocaleDateString(),
       time: now.toLocaleTimeString(),
       action: action
     };
   
     let records = JSON.parse(localStorage.getItem("attendanceRecords")) || [];
     records.push(record);
     localStorage.setItem("attendanceRecords", JSON.stringify(records));
     loadAttendance();
   }
   
   function loadAttendance() {
     const tableBody = document.querySelector("#attendanceTable tbody");
     tableBody.innerHTML = "";
     const records = JSON.parse(localStorage.getItem("attendanceRecords")) || [];
   
     records.forEach(r => {
       const row = <tr><td>${r.date}</td><td>${r.time}</td><td>${r.action}</td></tr>;
       tableBody.innerHTML += row;
     });
   }
   
   if (window.location.pathname.includes("dashboard.html")) {
     loadAttendance();
   }