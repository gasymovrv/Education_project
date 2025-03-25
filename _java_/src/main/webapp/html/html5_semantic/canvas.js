function canvasSmile() {
    var drawingCanvas = document.getElementById('smile');
    if(drawingCanvas && drawingCanvas.getContext) {
        var context = drawingCanvas.getContext('2d');
        // Рисуем окружность
        context.strokeStyle = "#000";
        context.fillStyle = "#fc0";
        context.beginPath();
        context.arc(100,100,50,0,Math.PI*2,true);
        context.closePath();
        context.stroke();
        context.fill();
        // Рисуем левый глаз
        context.fillStyle = "#fff";
        context.beginPath();
        context.arc(84,90,8,0,Math.PI*2,true);
        context.closePath();
        context.stroke();
        context.fill();
        // Рисуем правый глаз
        context.beginPath();
        context.arc(116,90,8,0,Math.PI*2,true);
        context.closePath();
        context.stroke();
        context.fill();
        // Рисуем рот
        context.beginPath();
        context.moveTo(70,115);
        context.quadraticCurveTo(100,130,130,115);
        context.quadraticCurveTo(100,150,70,115);
        context.closePath();
        context.stroke();
        context.fill();
    }
}

function canvasRain() {
    //initial
    var w = rain.width = window.innerWidth,
        h = rain.height = window.innerHeight,
        ctx = rain.getContext('2d'),

        //parameters
        total = w,
        accelleration = .05,

        //afterinitial calculations
        size = w / total,
        occupation = w / total,
        repaintColor = 'rgba(0, 0, 0, .04)'
    colors = [],
        dots = [],
        dotsVel = [];

//setting the colors' hue
//and y level for all dots
    var portion = 360 / total;
    for (var i = 0; i < total; ++i) {
        colors[i] = portion * i;

        dots[i] = h;
        dotsVel[i] = 10;
    }

    function anim() {
        window.requestAnimationFrame(anim);

        ctx.fillStyle = repaintColor;
        ctx.fillRect(0, 0, w, h);

        for (var i = 0; i < total; ++i) {
            var currentY = dots[i] - 1;
            dots[i] += dotsVel[i] += accelleration;

            ctx.fillStyle = 'hsl(' + colors[i] + ', 80%, 50%)';
            ctx.fillRect(occupation * i, currentY, size, dotsVel[i] + 1);

            if (dots[i] > h && Math.random() < .01) {
                dots[i] = dotsVel[i] = 0;
            }
        }
    }

    anim();
}