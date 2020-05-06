const track = document.querySelector('.carousel_track');
const slides = Array.from(track.children);
const next =document.querySelector('.next');
const prev = document.querySelector('.prev');
const nav = document.querySelector('.carousel_nav');
const dots = Array.from(nav.children);

const slideWidth = slides[0].getBoundingClientRect().width;

const setSlidePosition = (slide, index) =>{
slide.style.left = slideWidth * index  + 'px';
}

slides.forEach(setSlidePosition);

const moveToSlide  = (track, currentSlide, targetSlide) => {
track.style.transform = 'translateX(-' + targetSlide.style.left
currentSlide.classList.remove('current-slide'); //looking in a class list. dont need the dot
targetSlide.classList.add('current-slide');

}

const updateDots = (currentDot, targetDot) =>{
currentDot.classList.remove('current-slide');
targetDot.classList.add('current-slide');
}

const hideShowArrows = (slides, prev, next, targetIndex) =>{
if(targetIndex === 0){
prev.classList.add('is-hidden');
next.classList.remove('is-hidden');
}else if (targetIndex === slides.length -1){
    prev.classList.remove('is-hidden');
    next.classList.add('is-hidden');
    } else{
    prev.classList.remove('is-hidden');
    next.classList.remove('is-hidden');

}
}


prev.addEventListener('click', e =>{
const currentSlide = track.querySelector('.current-slide');
const prevSlide = currentSlide.previousElementSibling;

const currentDot = nav.querySelector('.current-slide');
const prevDot = currentDot.prevElementSibling;
const prevIndex = slides.findIndex(slide => slide === prevSlide);


moveToSlide(track, currentSlide, prevSlide);
updateDots(currentDot,prevDot);
hideShowArrows(slides, prev, next, prevIndex);

})

next.addEventListener('click', e =>{
const currentSlide = track.querySelector('.current-slide');
const nextSlide = currentSlide.nextElementSibling;

const currentDot = nav.querySelector('.current-slide');
const nextDot = currentDot.nextElementSibling;
const nextIndex = slides.findIndex(slide => slide === nextSlide);

moveToSlide(track, currentSlide, nextSlide);
updateDots(currentDot,nextDot);
hideShowArrows(slides, prev, next, nextIndex);

})

nav.addEventListener('click', e => {
const targetDot = e.target.closest('button');
 if(!targetDot) return;

 const currentSlide = track.querySelector('.current-slide')
  const currentDot = nav.querySelector('.current-slide')
  const targetIndex = dots.findIndex(dot  => dot  === targetDot)
  const targetSlide = slides[targetIndex]

moveToSlide(track, currentSlide, targetSlide);

updateDots(currentDot,targetDot);
hideShowArrows(slides, prev, next, targetIndex);
})