const {defineStore} = Pinia

const useCommonsReplyStore = defineStore('commons_reply',{
	state:()=>({
		list:[],
		curpage:1,
		startPage:0,
		endPage:0,
		totalpage:0,
		cno:0,
		sessionId:'',
		page:0,
		msg:'',
		count:0
		// update 관련
	}),
	getters:{
		range:(state)=>{
			const arr = []
			for(let i = state.startPage; i <= state.endPage; i++){
				arr.push(i)
			}
			return arr
		}
	},
	actions:{
		setPageData(data){
			this.list = data.list
			this.curpage = data.curpage
			this.startPage = data.startPage
			this.endPage = data.endPage
			this.cno = data.cno
			this.totalpage = data.totalpage
			this.count = data.count
		},
		movePage(page){
			this.curpage = page
			this.commonsListData(this.cno)
		},
		async commonsListData(cno){
			this.cno = cno
			const res = await api.get('/commons/list_vue/',{
				params:{
					page : this.curpage,
					cno:cno
					
				}
			})
			this.setPageData(res.data)
			
		},
		async commonsInsert(msgRef){
			if(this.msg === ''){
				msgRef?.focus()
				return
			}
			const res = await api.post('/commons/insert_vue/',{
				cno: this.cno,
				msg: this.msg,
				
			})
			this.setPageData(res.data)
			this.msg=''
		},
		// 삭제
		async commonsDelete(no){
			const res = await api.delete('/commons/delete_vue/',{
				params:{
					no:no,
					cno:this.cno,
					page:this.curpage
				}
				
			})
			this.setPageData(res.data)
		}
		// update
		
		// reply
	}
})