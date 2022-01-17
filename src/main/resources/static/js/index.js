window.onload = function () {
    const sqlForm = document.getElementById("sql-form");
    sqlForm.onsubmit = event => {
        event.preventDefault();
        const formData = new FormData(sqlForm);

        if (formData.get("client") != null){
            const input = formData.get("name");
            const black_list = ["select", "delete", "update", "drop", "exists", "where", "=", ";", "from"];
            for (const item of black_list){
                if (input.toLowerCase().includes(item)){
                    document.getElementById("result").innerHTML = `<p class='error'>Name must not contain '${item}'</p>`;
                    return;
                }
            }
        }

        const xmlHttp = new XMLHttpRequest();
        let url = "http://localhost:8080/person?name="+formData.get("name")+"&safe=";
        if (formData.get("safe") != null){
            url = url + "on"
        }else{
            url = url + "off"
        }
        xmlHttp.open("GET", url, true);
        xmlHttp.onreadystatechange = function (){
            if (xmlHttp.readyState === xmlHttp.DONE && xmlHttp.status === 200){
                const response = JSON.parse(xmlHttp.responseText);

                if (response.length === 0){
                    document.getElementById("result").innerHTML = "<p>No data</p>"
                }else{
                    let html = "<table>" +
                        "<tr>" +
                        "<th>ID</th>"+
                        "<th>First name</th>"+
                        "<th>Last name</th>"+
                        "<th>Sex</th>"+
                        "</tr>";
                    for (const item of response){
                        html += `<tr>
                                <td>${item.id}</td>
                                <td>${item.firstName}</td>
                                <td>${item.lastName}</td>
                                <td>${item.sex}</td>
                            </tr>`;
                    }
                    html += "</table>";
                    document.getElementById("result").innerHTML = html;
                }


            }
        }
        xmlHttp.send();

    };
}