const track = document.querySelector('.carousel_track');
const slides = Array.from(track.children);
const next = document.querySelector('.next');
const prev = document.querySelector('.prev');
const dotsNav = document.querySelector('.carousel_nav');
const dots = Array.from(dotsNav.children);
const slideWidth = slides[0].getBoundingClientRect().width;


const setSlidePosition = (slide, index) => {
  slide.style.left = slideWidth * index + 'px';
}
slides.forEach(setSlidePosition);


const moveToSlide = (currentSlide, targetSlide, targetDot, targetIndex) => {
  const currentDot = dotsNav.querySelector('.current-slide');
  track.style.transform = 'translateX(-'+ targetSlide.style.left + ')';
  currentSlide.classList.remove('current-slide');
  targetSlide.classList.add('current-slide');

  currentDot.classList.remove('current-slide');
  targetDot.classList.add('current-slide');


  if (targetIndex === 0) {
    prev.classList.add('is-hidden');
    next.classList.remove('is-hidden');
  } else if (targetIndex == slides.length - 1) {
    prev.classList.remove('is-hidden');
    next.classList.add('is-hidden');
  } else {
    prev.classList.remove('is-hidden');
    next.classList.remove('is-hidden');
  }
}

// when I click left, move slides to the left
prev.addEventListener('click', e => {
  const currentSlide = track.querySelector('.current-slide');
  const prevSlide = currentSlide.previousElementSibling;
  const slideIndex = slides.findIndex(slide => slide === prevSlide);
  const targetDot = dots[slideIndex];
  //move to the next slide
  moveToSlide(currentSlide, prevSlide, targetDot, slideIndex);
  if (prevSlide == currentSlide) {
    prevButton.classList.add('is-hidden');
    moveToSlide(currentSlide, prevSlide, targetDot);
  }
})


next.addEventListener('click', e => {
  const currentSlide = track.querySelector('.current-slide');
  const nextSlide = currentSlide.nextElementSibling;
  const slideIndex = slides.findIndex(slide => slide === nextSlide);
  const targetDot = dots[slideIndex];

//if it is out of range the index - move to next slide
  if (slideIndex != -1)
    moveToSlide(currentSlide, nextSlide, targetDot, slideIndex);
})


dotsNav.addEventListener('click', e => {

  const targetDot = e.target.closest('button');

  if (!targetDot) return;

  const currentSlide = track.querySelector('.current-slide');
  const targetIndex = dots.findIndex(dot => dot === targetDot);
  const targetSlide = slides[targetIndex];

  moveToSlide(currentSlide, targetSlide, targetDot, targetIndex);
})