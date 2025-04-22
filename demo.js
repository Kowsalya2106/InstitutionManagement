
document.addEventListener("DOMContentLoaded", function() {
    var form= document.getElementById("Registerform");
    
   if (!form) {
   console.error("Form element not found!");
   return;
   }
   let messageElement=document.getElementById("successMessage");

   form.addEventListener("submit", function(event) {
       event.preventDefault(); // Prevent form from refreshing the page

       let userData = {
           name: document.getElementById("name").value,
           age: document.getElementById("age").value,
           address:document.getElementById("address").value,
           email: document.getElementById("email").value,
           course: document.getElementById("course").value   
       };

       fetch("http://localhost:8080/api/register", {
           method:"POST",
           headers: {
               "Content-Type": "application/json"
           },
           body: JSON.stringify(userData)
       })
       .then(response => response.text()) // Handle response from server
       .then(data => {
           console.log("Server Response:",data);
           if(messageElement){
           messageElement.textContent="user registered successfully!";
           messageElement.style.color="green";
           messageElement.style.display="block";
           }
           form.reset();
       })
       .catch(error => 
       console.error("Error:", error));
       if(messageElement){
           messageElement.textContent="Registration failed! Try again";
           messageElement.style.color="red";
           messageElement.style.display="block"; 
           
       }
   });
   });
   //Retrive users table details
   document.addEventListener("DOMContentLoaded", function() {
    
// Fetch and display users when the page loads
fetchUsers();

function fetchUsers() {
   fetch("http://localhost:8080/api/users")
       .then(response => response.json())
       .then(users => {
           const tableBody = document.querySelector("#userTable tbody");
           tableBody.innerHTML = ""; // Clear existing rows

           users.forEach(user => {
               // Create a new row for each user
               const row = document.createElement("tr");
               row.innerHTML = `
                   <td>${user.id}</td> 
                   <td>${user.name}</td>
                   <td>${user.age}</td>
                   <td>${user.address}</td>
                   <td>${user.email}</td>
                   <td>${user.course}</td>
                   
                   <td>
                       <button onclick="updateUser(${user.id})">Update</button>
                       <button onclick="deleteUser(${user.id})">Delete</button>
                   
               `;
               tableBody.appendChild(row); // Append the row to the table
           });
       })
       .catch(error => console.error("Error fetching users:", error));
}
}); 
//delete method
//To click delete Button users deatails delete
function deleteUser(id) {
fetch(`http://localhost:8080/api/users/${id}`, {
   method: "DELETE"
})
.then(response => response.text()) // Handle response from server
.then(data => {
   console.log("Server Response:", data);
   fetchUsers(); // Refresh the list of users
})
.catch(error => console.error("Error:", error));
}

//update Method
function updateUser(id) {
var nameField = document.getElementById(`name-${id}`);
let ageField = document.getElementById(`age-${id}`);
let addressField = document.getElementById(`address-${id}`);
let emailField = document.getElementById(`email-${id}`);
let courseField = document.getElementById(`course-${id}`); 



if (!nameField || !ageField || !addressField || !emailField || !courseField) {
    let userInput=document.getElementById("userTable")

    if(!userInput){
        console.error("user inputs added successfully")
    }
    else
    {
        console.error("missing input values")
    }
   console.error("Input fields not found for user ID:",id);
  // alert ("Error:missing input values")
   return;
}
let updatedUser = {
   name:nameField.value,
   age:ageField.value,
   address:addressField.value,
   email:emailField.value,
   course:courseField.value,   
};

fetch(`http://localhost:8080/api/users/${id}`, {
   method: "PUT",
   headers: {
       "Content-Type": "application/json"
   },
   body: JSON.stringify(updatedUser)
})
.then(response => {
   if (!response.ok) {
       throw new Error("Failed to update user");
   }
   return response.text();
})
.then(data => {
   alert("User updated successfully!");
   fetchUsers(); //Refresh the users 
})
.catch(error => console.error("Error updating user:", error));
}
