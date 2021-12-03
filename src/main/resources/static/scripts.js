function onSelectScript1() {
    let userId = document.getElementById("userId").textContent;
    let pageNumber = document.getElementById("pageNumber").textContent;
    let pageSize = document.getElementById("pageSize").textContent;
    let sortBy = document.getElementById("sortBy").textContent;

    let root = "/" + userId;
    let suffix = "?";
    suffix += "&pageNumber=" + (+pageNumber - 1);
    suffix += "&pageSize=" + +pageSize;
    suffix += "&sortBy=" + sortBy.toLowerCase();

    let httpRequest = new XMLHttpRequest();
    httpRequest.open("GET", root + suffix);
    httpRequest.send(null);

    httpRequest.onreadystatechange = (e) => {
        console.log(httpRequest.responseText)
    }

    return httpRequest;
}
