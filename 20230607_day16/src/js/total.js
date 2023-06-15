fetch('http://localhost:5501/jsp/state/data.json')
// 기본 전체 테이블 Default
  .then(response => response.json())
  .then(jsonData => {
    const jsonObj = jsonData;
    const outputCotentEle = document.getElementById('outputContent');
    outputCotentEle.innerHTML = getContent(jsonObj);
    // console.log(jsonData);

    // 정렬 셀렉션 따라 테이블 재생성 
    const sortTbl = document.getElementById('sortTbl');
    sortTbl.addEventListener('change', () => {
      const jsonObj = jsonData;

      const selectedOption = sortTbl.options[sortTbl.selectedIndex].text;
      if (selectedOption !== '정렬순') {
        const outputSelectEle = document.getElementById('outputContent');
        outputSelectEle.innerHTML = searchClick(jsonObj);
      }
    });

    // 도서 이름, 번호 따라 검색, 테이블 재생성
    const searchButton = document.getElementById('searchBookButton');
    searchButton.addEventListener('click', () => {
      const jsonObj = jsonData;

      const outputButtonEle = document.getElementById('outputContent');
      outputButtonEle.innerHTML = findText(jsonObj);
      console.log(outputButtonEle.innerText);

    });

  // 날짜에 따라서 테이블 생성
    const dateSearchButton = document.getElementById('searchDateButton');
    dateSearchButton.addEventListener('click', () => {
      const jsonObj = jsonData;

      const outputButtonEle = document.getElementById('outputContent');
      outputButtonEle.innerHTML = dateSearch(jsonObj);
      console.log(outputButtonEle.innerText);
  
    });
    
     
    return fetch('http://localhost:5501/jsp/state/data.json')
    
  })
// 
//   .then(response => response.json())
//   .then(jsonData_ => {
//     const jsonObj_ = jsonData_;

//     const sortTbl = document.getElementById('sortTbl');
//     sortTbl.addEventListener('change', () => {
//       const selectedOption = sortTbl.options[sortTbl.selectedIndex].text;
//       if (selectedOption !== '정렬순') {
//         const outputSelectEle = document.getElementById('outputContent');
//         outputSelectEle.innerHTML = searchClick(jsonObj_);
//       }
//     });
//     // console.log(jsonData_);
//     return fetch('http://localhost:5501/jsp/state/data.json')
//   })

  .catch(error => {
    // 에러 처리
    console.error('Error:', error);
  })


const setRow = (obj)=>{
    const{
        book_no,
        title,
        memberNo,
        author,
        rentDate,
        rental_check,
        return_check,
    }= obj;
    const rowtext = `<tr><td>${book_no}</td><td>${title}</td><td>${memberNo}</td><td>${author}</td><td>${rentDate}</td><td>${rental_check}</td><td>${return_check}</td></tr>`;
    return rowtext;
};

const getContent=(data)=> {
    const header = data.header;
    const content = data.contents;  

    const outputTotalNumEle = document.getElementById('totalNum');
    outputTotalNumEle.innerHTML = "<b>전체</b>"+ "<b>"+content.length+"</b>" + "<b>건</b>";

    const theadStr = "<thead><th>도서번호</th><th>도서명</th><th>회원번호</th><th>회원명</th><th>대출일</th><th>상태</th><th>반납</th></thead>";
    let tbodyStr = "<tbody>";

    // for(let i =0;i<content.length;i+=5){ 

      for(let j = 0 ;j < content.length ; j++){

         var tableRow = {
              book_no : content[j].book_no,
              title : content[j].title,
              memberNo : "00000"+j,
              author : content[j].author,
              rentDate : "2023-06-0"+j,
              rental_check : content[j].rental_check,
              return_check : '<button class="btn_s_red"> 반납 </button>'

         };

         tbodyStr += setRow(tableRow);
       }
    // }
    tbodyStr+="</tbody>";

    // // Pagenation 출력
    // const outputButtonEle = document.getElementById('pagenation');
    // outputButtonEle.innerHTML = pagenation(content.length, );
    

    return (theadStr + tbodyStr);

}

const searchClick=(jsonObj_)=>{

  var num = document.getElementById("sortTbl").selectedIndex;
  var arr = document.getElementById("sortTbl").options;

  let clickSearch = arr[num].text;

  console.log(clickSearch);

  const content = jsonObj_.contents;

  const theadStr = "<thead><th>도서번호</th><th>도서명</th><th>회원번호</th><th>회원명</th><th>대출일</th><th>상태</th><th>반납</th></thead>";
  let tbodyStr = "<tbody>";

  var nameResult = [];

  var tbodyStrArr = [];

  for(let i =0;i<30;i++){

      var tableRows = {
          book_no : content[i].book_no,
          title : content[i].title,
          memberNo : "00000"+i,
          author : content[i].author,
          rentDate : "2023-07-"+i,  
          rental_check : content[i].rental_check,
          return_check : '<button class="btn_s_red"> 반납 </button>'

      };
      tbodyStrArr.push(tableRows);      

    }

  if(clickSearch == "대출일"){
    console.log("대출일 in");

    nameResult = tbodyStrArr.sort(function(a,b){
      let x = a.rentDate;
      let y = b.rentDate;

      return new Date(x).getDate() - new Date(y).getDate();

      // if(x<y){
      //   return -1;
      // }
      // if(x>y){
      //   return 1;
      // }
      // return 0;
    });

    const outputTotalNumEle = document.getElementById('totalNum');
    outputTotalNumEle.innerHTML = "<b>전체</b>"+ nameResult.length + "<b>건</b>";

    console.log(nameResult);
  }

  if(clickSearch == "도서명"){
    console.log("도서명 안");
    nameResult = tbodyStrArr.sort(function(a,b){
      let x = a.title.toLowerCase();
      let y = b.title.toLowerCase();
      if(x<y){
        return -1;
      }
      if(x>y){
        return 1;
      }
      return 0;
    });
    
    const outputTotalNumEle = document.getElementById('totalNum');
    outputTotalNumEle.innerHTML = "<b>전체</b>"+ nameResult.length + "<b>건</b>";
  }

  if(clickSearch == "회원명"){
    console.log("회원명 in");

    nameResult = tbodyStrArr.sort(function(a,b){
      let x = a.author.toLowerCase();
      let y = b.author.toLowerCase();
      if(x<y){
        return -1;
      }
      if(x>y){
        return 1;
      }
      return 0;
    });

    const outputTotalNumEle = document.getElementById('totalNum');
    outputTotalNumEle.innerHTML = "<b>전체</b>"+ nameResult.length + "<b>건</b>";
  }

  for(let j =0;j<nameResult.length;j++){
    tbodyStr += setRow(nameResult[j]);
  }
  console.log(tbodyStr);

  tbodyStr+="</tbody>";
  return (theadStr + tbodyStr);
  

}

const findText=(jsonObjt)=>{

  var nameResult = [];

  var tbodyStrArr = [];

  var num = document.getElementById("selectBook").selectedIndex;
  var arr = document.getElementById("selectBook").options;

  let clickSearch = arr[num].text;

  const content = jsonObjt.contents;

  const theadStr = "<thead><th>도서번호</th><th>도서명</th><th>회원번호</th><th>회원명</th><th>대출일</th><th>상태</th><th>반납</th></thead>";
  let tbodyStr = "<tbody>";

  console.log(clickSearch);

  const searchBook = document.getElementById('selectBook');
  const selectedOption = searchBook.options[searchBook.selectedIndex].text;
 
  for(let i =0;i<30;i++){

    var tableRows = {
        book_no : content[i].book_no,
        title : content[i].title,
        memberNo : "00000"+i,
        author : content[i].author,
        rentDate : "2023-07-0"+i,
        rental_check : content[i].rental_check,
        return_check : '<button class="btn_s_red"> 반납 </button>'

    };
    tbodyStrArr.push(tableRows);      

  }
 
  const name = document.getElementById('searchBook').value;

//  문자열 확인
  if(typeof tbodyStrArr[0].book_no === 'string'){
    console.log("true");
  }

 
  if (selectedOption !== '전체') {

   if(clickSearch == "도서번호"){
     console.log("도서번호 in");

      for(let j =0; j < tbodyStrArr.length;j++){
      if(tbodyStrArr[j].book_no == name){



          // console.log(typeof tbodyStrArr[0].book_no);


       console.log("book_no 찾음");
       nameResult.push(tbodyStrArr[j]);
        } 
      }

      const outputTotalNumEle = document.getElementById('totalNum');
      outputTotalNumEle.innerHTML = "<b>전체</b>"+ nameResult.length + "<b>건</b>";

    }

   if(clickSearch == "도서명"){
     console.log("도서명 안");

      for(let j =0; j < tbodyStrArr.length;j++){
        if(tbodyStrArr[j].title == name){
          console.log("title 찾음");
          nameResult.push(tbodyStrArr[j]);
          } 
        }

        const outputTotalNumEle = document.getElementById('totalNum');
        outputTotalNumEle.innerHTML = "<b>전체</b>"+ nameResult.length + "<b>건</b>";
    }
  }else{
    for(let j =0; j < tbodyStrArr.length;j++){
      // tbodyStrArr[j].book_no 는 name의 타입이기 때문에 문자열 변환 후 indexOf 해야한다.
      let bookNo = tbodyStrArr[j].book_no;
      let bookNo_ = bookNo.toString();

      let titleName = tbodyStrArr[j].title;
      let titleName_ = titleName.toString();

      if(bookNo_.indexOf(name) != -1){
       console.log("책번호로 찾음");
       nameResult.push(tbodyStrArr[j]);
        } 

      if(titleName_.indexOf(name) != -1){
          console.log("책이름으로 찾음");
          nameResult.push(tbodyStrArr[j]);
        } 
      }

      const outputTotalNumEle = document.getElementById('totalNum');
      outputTotalNumEle.innerHTML = "<b>전체</b>"+ nameResult.length + "<b>건</b>";
  }



  for(let k =0;k<nameResult.length;k++){
    tbodyStr += setRow(nameResult[k]);
  }

  console.log(tbodyStr);
  
  tbodyStr+="</tbody>";
  return (theadStr + tbodyStr);
  
}

// const pagenation=(totalCnt, curentPg)=>{

//   // 총 데이터 갯수
//   const totalCount = totalCnt;
//   // 한 페이지당 데이터 갯수(상수)
//   const pageCount = 5;

//   // 총 페이지 계산
//   let totalPage =Math.ceil(totalCount/pageCount);

//   //현재 페이지
//   const curentPage = curentPg;

//   //현재 어디 페이지 그룹인지 (ex/ 첫번째 그룹= 버튼1,2,3,4,5 다섯개 / 두번째 그룹 = 버튼 6,7,8,9,10 다섯개)
//   let pageGroup = Math.ceil(curentPage / pageCount);

//   let lastNumber = pageGroup * pageCount;
//   if(lastNumber > totalPage){
//     lastNumber = totalPage;
//   }
//   let firstNumber = lastNumber - (pageCount - 1);

//   const next = lastNumber + 1;
//   const prev = firstNumber - 1;

//   let buttonStr;
//   //맨 처음 그룹에는 이전 버튼 없음 
//   if(prev == 0){

//     for(let i = firstNumber ; i <lastNumber; i++){
//       buttonStr += `<button class="pageNumber" type = "button" id="page_${i}" onclick="pagenation(``${totalCount}``,page_``${i})"> ${i} </button>`
//     }
  
//   //두번째 그룹 부터는 첫번째 그룹으로 돌아갈 수 있기 때문에 이전 버튼이 생김
//   }else{
//     buttonStr += `<button class="pageNumber" type = "button" id="page_${prev}" onclick="pagenation(``${totalCount}``,${prev})" > 이전 </button>`

//     for(let i = firstNumber ; i <lastNumber; i++){
//       buttonStr += `<button class="pageNumber" type = "button" id="page_${i}" onclick="pagenation(``${totalCount}``,page_``${i})"> ${i} </button>`
//     }
//   }

//   buttonStr += `<button class="pageNumber" type = "button" id="page_${next}" onclick="pagenation(``${totalCount}``,${next})"> 다음 </button>`

// }

const dateSearch=(jsonObj_)=>{

  var startDate = document.getElementById("startDate");
  var startDateStr = startDate.value;
  var startDateValue = new Date(startDateStr);
  var startDateMillis = startDateValue.getTime();

  var endDate = document.getElementById("endDate");
  var endDateStr = endDate.value;  
  var endDateValue = new Date(endDateStr);
  var endDateMillis = endDateValue.getTime();



  const content = jsonObj_.contents;

  const theadStr = "<thead><th>도서번호</th><th>도서명</th><th>회원번호</th><th>회원명</th><th>대출일</th><th>상태</th><th>반납</th></thead>";
  let tbodyStr = "<tbody>";

  var nameResult = [];

  var tbodyStrArr = [];

  const fixedStartDate = new Date(2023,4,1); // 월을 0부터 시작하기 때문에 5로 설정해야 6월이 나온다, 근데 이 코드에서는 4로 설정해야한다 이유는 나중에 찾기로..

  const fixedEndDate = new Date(2023,4,30);

  const fixedstartDateStr = fixedStartDate.toLocaleDateString('en-CA');
  const fixedendDateStr = fixedEndDate.toLocaleDateString('en-CA');



  for(let i =0;i<content.length;i++){

    const test = new Date(fixedEndDate.getTime() + Math.random() * (fixedEndDate.getTime() - fixedStartDate.getTime()));
    const rentdateStr = test.toLocaleDateString('en-CA');

      var tableRows = {
          book_no : content[i].book_no,
          title : content[i].title,
          memberNo : "00000"+i,
          author : content[i].author,
          rentDate : rentdateStr,  
          rental_check : content[i].rental_check,
          return_check : '<button class="btn_s_red"> 반납 </button>'

      };



      console.log(tbodyStrArr.length);

      if(startDateMillis <= test && endDateMillis >= test){
        //startDate 보다 같거나 크고 endDate 보다 같거나 작은 rentDate 를 nameResult에 push
        console.log("push 됐다");
        console.log(tableRows.rentDate);
        console.log(startDateStr);
        console.log(endDateStr);
        console.log("-----------");
        tbodyStrArr.push(tableRows);  
      }
    }

    console.log(tbodyStrArr.length);

    nameResult = tbodyStrArr ;// 이렇게 해서 넘겨야함

    const outputTotalNumEle = document.getElementById('totalNum');
    outputTotalNumEle.innerHTML = "<b>전체</b>"+ nameResult.length + "<b>건</b>";


  for(let j =0;j<nameResult.length;j++){
    tbodyStr += setRow(nameResult[j]);
  }
  console.log(tbodyStr);

  tbodyStr+="</tbody>";
  return (theadStr + tbodyStr);
  
}
