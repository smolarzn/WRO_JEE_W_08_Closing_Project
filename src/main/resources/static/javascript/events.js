document.addEventListener("DOMContentLoaded", function () {

    //quiz - zaznaczenie radio buttonów
    var inputGroups = document.querySelectorAll(".inputs");
    inputGroups.forEach(el => {
        var radioButton = el.firstChild.nextSibling;
        radioButton.checked = true;
    });

    //powiększanie zdjęcia
    const myImg = document.getElementById("image");
    let currWidth = myImg.clientWidth;
    let currHeight = myImg.clientHeight;

    const onclick = document.getElementById("zoomin");
    onclick.addEventListener("click", function () {
        console.log(myImg);
        console.log(onclick);

        if(currWidth > 500){
            alert("Maximum zoom-in level reached.");
        } else{
            myImg.style.width = (currWidth*1.4) + "px";
            myImg.style.height = (currHeight*1.4) + "px";
        }
    });

    const zoomout = document.getElementById("zoomout");
    console.log(zoomout)
    zoomout.addEventListener("click", function () {
        if(myImg.clientWidth < 50){
            alert("Maximum zoom-out level reached.");
        } else{
            myImg.style.width =  300 + "px";
            myImg.style.height = 200 + "px";
        }
    });

});
