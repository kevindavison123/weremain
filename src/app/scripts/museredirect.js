/*
 Copyright 2011-2016 Adobe Systems Incorporated. All Rights Reserved.
*/
typeof Muse=="undefined"&&(Muse={});Muse.Redirect={};Muse.Redirect.domPrefixes=["Webkit","Moz","O","ms","Khtml"];Muse.Redirect.Touch=function(){if(navigator.maxTouchPoints>1)return!0;if(window.matchMedia&&window.matchMedia("(-moz-touch-enabled)").matches)return!0;for(var a=0,b=Muse.Redirect.domPrefixes.length;a<b;a++){var c=Muse.Redirect.domPrefixes[a]+"MaxTouchPoints";if(c in navigator&&navigator[c])return!0}try{return document.createEvent("TouchEvent"),!0}catch(d){}return!1}();
Muse.Redirect.readCookie=function(a){a+="=";for(var b=document.cookie.split(";"),c=0;c<b.length;c++){for(var d=b[c];d.charAt(0)==" ";)d=d.substring(1,d.length);if(d.indexOf(a)==0)return d.substring(a.length,d.length)}return null};
Muse.Redirect.redirect=function(a,b,c){if(Muse.Redirect.readCookie("inbrowserediting")!="true"){var d,g=Muse.Redirect.readCookie("devicelock");g=="phone"&&c?d=c:g=="tablet"&&b&&(d=b);if(g!=a&&!d)if(window.matchMedia)window.matchMedia("(max-device-width: 415px)").matches&&c?d=c:window.matchMedia("(max-device-width: 960px)").matches&&b&&Muse.Redirect.Touch&&(d=b);else{var a=Math.min(screen.width,screen.height)/(window.devicePixelRatio||1),g=window.screen.systemXDPI||0,f=window.screen.systemYDPI||0,
g=g>0&&f>0?Math.min(screen.width/g,screen.height/f):0;(a<=370||g!=0&&g<=3)&&c?d=c:a<=960&&b&&Muse.Redirect.Touch&&(d=b)}if(d)document.location=d,document.write('<style type="text/css">body {visibility:hidden}</style>')}};
;(function(){if(!("undefined"==typeof Muse||"undefined"==typeof Muse.assets)){var a=function(a,b){for(var c=0,d=a.length;c<d;c++)if(a[c]==b)return c;return-1}(Muse.assets.required,"museredirect.js");if(-1!=a){Muse.assets.required.splice(a,1);for(var a=document.getElementsByTagName("meta"),b=0,c=a.length;b<c;b++){var d=a[b];if("generator"==d.getAttribute("name")){"2015.1.1.343"!=d.getAttribute("content")&&Muse.assets.outOfDate.push("museredirect.js");break}}}}})();
