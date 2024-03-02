//Parallax
$(document).mousemove(function (e) {
    var coords = $(window).width() / 3
    console.log(coords)
    if (e.pageX < coords) {
      $('#manchas .boxInfo').removeClass('focusOff')
      $('#manchas .boxInfo.agencia').addClass('focusOff')
      $('#manchas .circle').removeClass('focus')
      $('#manchas .circle.rosa').addClass('focus')
    } else if (e.pageX > coords && e.pageX < coords * 2) {
      $('#manchas .boxInfo').removeClass('focusOff')
      $('#manchas .circle').removeClass('focus')
    } else {
      $('#manchas .boxInfo').removeClass('focusOff')
      $('#manchas .boxInfo.estudio').addClass('focusOff')
      $('#manchas .circle').removeClass('focus')
      $('#manchas .circle.blanco, #manchas .circle.blancoBig').addClass('focus')
    }
  })