const {defineStore} = Pinia

// static 변수 => 멤버
const initalStore = {
	list: [],
	curpage: 1,
	totalpage: 0,
	endPage: 0,
	startPage: 0,
	fd:'해수욕장',
	selected:'12'
}

const useJejuStore = defineStore('jeju_find',{
	// 모든 컴포넌트에서 사용이 가능하게 공유 변수 설정
	state:()=> initalStore,
	getters:{
		range:(state)=>{
			const arr = []
			for(let i = state.startPage; i<= state.endPage; i++){
				arr.push(i)
			}
			return arr
		}
		
	},
	// 기능 => 사용자 요청
	actions:{
		// 서버로부터 요청값 받기
		async jejuFindData(){
			const res = await api.get('/jeju/find_vue/',{
				params:{
					page:this.curpage,
					selected:this.selected,	
					fd:this.fd
					
				}
			})
			this.setPageData(res.data)
		},		
		setPageData(data){
			this.list = data.list
			this.curpage = data.curpage
			this.totalpage = data.totalpage
			this.startPage = data.startPage
			this.endPage = data.endPage
			this.address = data.address
			this.fd = data.fd
			this.selected = data.selected
		},
		movePage(page){
			this.curpage = page	
			this.jejuFindData()
		},
		find(findRef){
			if(this.fd === ''){
				findRef?.focus()
				return
			}
			this.curpage = 1
			this.jejuFindData()
		}
		
	}
})