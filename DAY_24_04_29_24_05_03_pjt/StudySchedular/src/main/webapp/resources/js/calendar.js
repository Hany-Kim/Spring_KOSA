// 달력 만들기

let date = new Date();
var myMonth = 0; // 렌더링 될때 어떤 달의 날짜인지 확인할 '달'변수
const renderCalendar = () => {

  const viewYear = date.getFullYear(); // 해당 년 받아오기
  const viewMonth = date.getMonth(); // 해당 월 받아오기
  myMonth = viewMonth; // 렌더링 될때 어떤 달의 날짜인지 확인할 '달'변수
  var monthNames = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"
  ]; // 달 이름을 영어로 출력하기 위한 배열

  document.querySelector(".year-month").textContent = `${viewYear} ${monthNames[viewMonth]}`;

  const prevLast = new Date(viewYear, viewMonth, 0); // 지난달의 마지막 날 Date 객체
  const thisLast = new Date(viewYear, viewMonth + 1, 0); // 이번달의 마지막 날 Date 객체

  const PLDate = prevLast.getDate(); // 지난달의 마지막 날짜
  const PLDay = prevLast.getDay(); // 지난달의 마지막 요일

  const TLDate = thisLast.getDate(); // 이번달의 마지막 날짜
  const TLDay = thisLast.getDay(); // 이번달의 마지막 요일

  // 날짜들을 담아둘 배열
  const prevDates = []; // 이전달 날짜
  const thisDates = [...Array(TLDate + 1).keys()].slice(1);
  const nextDates = []; // 다음달 날짜

  // Array(n) => 길이가 n인 배열 생성
  // keys() => 0부터 n -1 까지의 배열 반복자 생성(내부요소 순회) ==> 이번달 마지막 날짜 + 1을 n에 전달
  // slice(1) => 제일 앞에 있는 0을 없애기 위해

  if (PLDay !== 6) { // 이전 달을 표현할 날짜 생성 (지난달 마지막 요일이 토요일(6) 이면 굳이 그릴 그릴 필요 없음)
    for (let i = 0; i < PLDay + 1; i++) {
      prevDates.unshift(PLDate - i);
    }
  }

  //unshift() => 배열의 앞에 아이템을 추가한다. 새로운 요소를 배열의 맨 앞쪽에 추가하고 새로운 길이 반환
  // 0부터 시작해서 지난달 마지막 요일이 될 때까지 반복하게 작성, 지난달의 마지막 날짜부터 1씩 줄어든 값을 배열 앞쪽으로 채워넣음

  for (let i = 1; i < 7 - TLDay; i++) {
    nextDates.push(i); // 이번달 마지막 날짜의 요일을 기준으로 필요한 개수를 파악해서 1부터 1씩 증가시키며 하나씩 채워넣음
  }

  // push() => 배열의 끝에 하나 이상의 요소를 추가하고 배열의 새로운 길이 반환
  const dates = prevDates.concat(thisDates, nextDates); // concat 메서드를 통해서 세 배열을 합침
  const firstDateIndex = dates.indexOf(1); // 지난달 부분을 알아내기 위함
  const lastDateIndex = dates.lastIndexOf(TLDate); // 다음달 부분을 알아내기 위함

  dates.forEach((date, i) => { // 이전달과 다음달 부분의 투명도 조절 위함
    const condition = i >= firstDateIndex && i < lastDateIndex + 1
                      ? 'this' // 이번달
                      : 'other'; // 나머지 (span대그로 감싸 classa 로 지정)
    //dates[i] = `<button class="date"><span class="${condition}">${date}</span></button>`;
    //dates[i] = `<div class="date"><span class="${condition}">${date}</span></div>`;
    dates[i] = `<button class="date"><div class="${condition}" style="height:100%; width:100%"><span class="${condition}/${myMonth + 1}">${date}</span></div></button>`;
    //dates[i] = `<div class="${condition}-month"><button class="date"><span class="${condition}">${date}</span></button></div>`;
  })

  document.querySelector('.dates').innerHTML = dates.join('');

  const today = new Date(); // 오늘 날짜 표기하기 위해
  if (viewMonth === today.getMonth() && viewYear === today.getFullYear()) { // 현재 월을 보고 있는게 맞는지
    for (let date of document.querySelectorAll('.this')) { // this 태그 찾기
      if (+date.innerText === today.getDate()) { // +연산자로 숫자로 변경
        date.classList.add('today'); // 해당 태그에 today 클래스 추가
        break;
      }
    }
  }
}

renderCalendar();

const prevMonth = () => {
  date.setDate(1); // 1일로 날짜를 먼저 설정 (31일이 포함된 달의 경우 31일이 넘어가게 되기 때문!)
  date.setMonth(date.getMonth() - 1);
  renderCalendar();
}

const nextMonth = () => {
  date.setDate(1);
  date.setMonth(date.getMonth() + 1);
  renderCalendar();
}

const goToday = () => {
  date = new Date();
  renderCalendar();
}

const setInit = (todaym, month) => {
    goToday(); // 오늘 날짜로 돌려두고
    if(todaym > month){ // 이번달보다 이전 달
    	for(i=0; i<(todaym - month); i++){
    		prevMonth();
    	}
    } else if(todaym < month){ // 이번달보다 이후 달
    	for(i=0; i<(month - todaym); i++){
    		nextMonth();
    	}
    }
}

// 입력 날짜에 따른 달력
window.onload = function() {
	let today = new Date();
	const todaym = today.getMonth() + 1;
	const todayd = today.getDate();
	
    const start_date = document.getElementById('start_date');
    const end_date = document.getElementById('end_date');
    var dateItems = document.querySelectorAll('.this'); // 수정된 부분
    var startMonth = 0;
    var endMonth = 0;
    var startDay = 0;
    var endDay = 0;
    
    start_time.addEventListener('input', function() {
        const selectedDate = new Date(this.value);
        const year = selectedDate.getFullYear();
        const month = selectedDate.getMonth() + 1;
        startMonth = month;
        const day = selectedDate.getDate();
        startDay = day; // 시작날 세팅
        var innerEndDay = 0;
        if(endDay!== 0) {innerEndDay = endDay;}
        
        
        setInit(todaym, month);
        dateItems = document.querySelectorAll('.this'); // 다시담기
        
        if(startMonth !== endMonth){
        	if(startMonth < endMonth){
        		innerStartDay = 1;
        	}
        }
        
        dateItems.forEach(function(item) {
        	const dateDay = +item.innerText; // 수정된 부분
        	if (dateDay === day) {
            	item.style.backgroundColor = "#CFE8F4";
            } else {
            	item.style.backgroundColor = ""; // 다른 날짜는 강조를 제거합니다.
            }
        });
    });
    
    end_time.addEventListener('input', function() {
        const selectedDate = new Date(this.value);
        const year = selectedDate.getFullYear();
        const month = selectedDate.getMonth() + 1;
        const day = selectedDate.getDate();
        endMonth = month;
        endDay = day;
        var innerStartDay = startDay; // end_time안에서 사용한 이너 변수
        if(startDay!== 0) {innerStartDay = startDay;}
        
        setInit(todaym, month);
        dateItems = document.querySelectorAll('.this'); // 다시담기
        
        if(startMonth !== endMonth){ // 달이 다르고
        	if(startMonth < endMonth){ // 시작달보다 종료달이 넘어간경우
        		innerStartDay = 1;
        	}
        }
        
        var paintFlag = false;
        dateItems.forEach(function(item) {
        	const dateDay = +item.innerText;
        	
        	if(paintFlag == false){
        		if(dateDay === innerStartDay){
        			paintFlag = true;
        		}
        	} 
        	if(paintFlag == true) {
        		if(dateDay === day){
        			paintFlag = false;
        		}
        	}
        	
        	if(startMonth > month){paintFlag = false;} // 시작달이 종료달보다 늦음
        	if(startMonth == month) { // 같은 달 (시작 일 > 종료 일)
        		if(innerStartDay > day){ paintFlag = false; }
        	}
        	
        	// flag에 따라 색칠됨
        	if(paintFlag === true){
        		item.style.backgroundColor = "#CFE8F4";
        	} else if((startMonth <= month) && (dateDay == day)) { // 마지막 날 보정
        		if((startMonth == month) && (dateDay < innerStartDay)){ // 같은 달 인데 시작일 보다 종료일이 빠름
        			item.style.backgroundColor = "";
        		} else {
        			item.style.backgroundColor = "#CFE8F4";
        		}
        	} else {
        		item.style.backgroundColor = "";
        	}
        })
    });
};