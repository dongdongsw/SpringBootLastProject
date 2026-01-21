<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
   <table class="table">
      <tr>
         <td class="text-center"><h3>예약 목록</h3></td>
      </tr>
   </table>
   <div class="row" id="reserveApp">
   <table class="table">
      <thead>
         <tr class="danger">
            <th class="text-center">예약번호</th>
            <th class="text-center">아이디</th>
            <th class="text-center">업체명</th>
            <th class="text-center"></th>
            <th class="text-center">예약일</th>
            <th class="text-center">예약시간</th>
            <th class="text-center">인원</th>
            <th class="text-center">등록일</th>
            <th class="text-center"></th>            
         </tr>
      </thead>
      <tbody>
         <tr v-for="(vo,i) in store.reserve_list" :key="i" >
            <td class="text-center">{{vo.no}}</td>
            <td class="text-center">{{vo.id}}</td>
            <td class="text-center">{{vo.svo.title}}</td>
            <td class="text-center"><img :src="vo.svo.image1" style="width:30px;height:30px;"></td>
            <td class="text-center">{{vo.rday}}</td>
            <td class="text-center">{{vo.rtime}}</td>
            <td class="text-center">{{vo.rinwon}}</td>
            <td class="text-center">{{vo.dbday}}</td>
            <td class="text-center">
               <button class="btn btn-xs btn-warning" v-if="vo.isreserve===0" @click="store.reserveOk(vo.no, vo.id)">승인 대기</button>
               <span class="btn btn-xs btn-default" v-else>승인 완료</span>
               <button class="btn btn-xs btn-danger" style="margin-left: 1px;" v-if="vo.iscancel === 1" @click="store.reserveDelete(vo.no, vo.id)">취소</button>
            </td>            
         </tr>
      </tbody>
   </table>
   </div>
   <script src="/vue/axios.js"></script>
   <script src="/vue/reserve/adminStore.js"></script>
   <script>
   const {createPinia} = Pinia
   const {createApp,onMounted} = Vue
   const mypageApp = createApp({
      setup(){
      const store = useAdminStore()
      onMounted(()=>{
         store.dataRecv()
      })
      return {
         store
      }
      }
      
   })
   mypageApp.use(createPinia())
   mypageApp.mount('#reserveApp')
   </script>
</body>
</html>