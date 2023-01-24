function editModal(id) {
    fetch(url + id, {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json;charset=UTF-8'
        }
    }).then(res => {
        res.json().then(us => {

            document.getElementById('idEdit').value = us.id;
            document.getElementById('nameEdit').value = us.name;
            document.getElementById('lastNameEdit').value = us.lastname;
            document.getElementById('ageEdit').value = us.age;
            document.getElementById('passwordEdit').value = us.password;
            document.getElementById('emailEdit').value = us.email;

        })
    });
}

const editUser = document.getElementById("editUser");
editUser.addEventListener('submit', (e) => {
    e.preventDefault();
    let idValue = document.getElementById("idEdit").value;
    let nameValue = document.getElementById("nameEdit").value;
    let lastNameValue = document.getElementById("lastNameEdit").value;
    let ageValue = document.getElementById("ageEdit").value;
    let emailValue = document.getElementById("emailEdit").value;
    let passwordValue = document.getElementById("passwordEdit").value;
    let roles = editRoles(Array.from(document.getElementById("rolesEdit").selectedOptions).map(role => role.value));
    let newUser = {
        id: idValue,
        name: nameValue,
        lastName: lastNameValue,
        age: ageValue,
        email: emailValue,
        password: passwordValue,
        roles: roles
    }
    fetch(url + document.getElementById('idEdit').value, {
        method: "PATCH",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newUser)
    }).then(() => {
        document.getElementById("closeEditForm").click();
        getAllUsers()
    })
})
function editRoles(rols) {
    let roles = [];
    if (rols.indexOf("ADMIN") >= 0) {
        roles.push({"name": 'ADMIN'});
    }
    if (rols.indexOf("USER") >= 0) {
        roles.push({"name": 'USER'});
    }
    return roles;
}