document.getElementById("go").addEventListener("click", getTimeZone);

function getTimeZone() {

    var xhr = new XMLHttpRequest();
    var period = document.querySelector("#period").value;
    var user = document.querySelector("#user").value;
    xhr.open('GET', "/artists?user=" + user + "&period=" + period, true);
    xhr.send();
    xhr.onreadystatechange = processRequest;

    function processRequest(e) {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var response = JSON.parse(xhr.responseText);
            var compiledTemplate = Handlebars.compile(document.querySelector("#top-artists-template").innerHTML);
            if (response.errorMessage == null) {
                console.log(response)
                document.querySelector("#top-artists-list-container").innerHTML = compiledTemplate(response);
                document.querySelector("#error-message").innerHTML = "";
            } else {
                document.querySelector("#top-artists-list-container").innerHTML = "";
                document.querySelector("#error-message").innerHTML = "<span>" + response.errorMessage + "</span>";
            }
            document.querySelector("#top-artists").style.cssText = 'padding: 1.5rem';
        }
    }

}