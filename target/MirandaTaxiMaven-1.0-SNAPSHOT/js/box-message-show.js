/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
"use strict";

var pageEndVisible = function (pageEnd) {
    try {
        $(pageEnd).css("z-index", "998");
        $(pageEnd).css("height", "100%");
        $(pageEnd).css("width", "100%");
        $(pageEnd).css("position", "absolute");
        $(pageEnd).css("top", "0");

        $("html, body").animate({scrollTop: 0}, 500, 'swing');//quando clicar em "CONTRATAR", deslizar até o topo em 0.5s
        if (document.getElementsByTagName("body")[0].style.overflow === "hidden") {
            $("body").css("overflow", "auto");
        } else {
            $("body").css("overflow", "hidden");
        }

        $(pageEnd).fadeToggle("slow");

    } catch (error) {
    }
},
boxMessageShow = function (messageBox) {
    $(messageBox).fadeToggle("slow");
    $(messageBox).focus();
};







