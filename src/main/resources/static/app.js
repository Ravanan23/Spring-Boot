// const apiUrl = "/api/users";

// // GET all users
// function getUsers() {
//     fetch(apiUrl)
//         .then(res => res.json())
//         .then(data => {
//             const ul = document.getElementById("userList");
//             ul.innerHTML = "";
//             data.forEach(u => {
//                 let li = document.createElement("li");
//                 li.textContent = `${u.id} - ${u.name} - ${u.email}`;
//                 ul.appendChild(li);
//             });
//         });
// }

// // CREATE user
// function createUser() {
//     const name = document.getElementById("name").value;
//     const email = document.getElementById("email").value;

//     fetch(apiUrl, {
//         method: "POST",
//         headers: { "Content-Type": "application/json" },
//         body: JSON.stringify({ name, email })
//     })
//     .then(res => res.json())
//     .then(data => {
//         alert("User created: " + data.id);
//         getUsers();
//     });
// }
