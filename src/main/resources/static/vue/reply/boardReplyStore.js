const {defineStore} = Pinia

const useBoardReplyStore = defineStore('board_reply',{
	
	state:()=>({
		list: [],
		count: 0,
		bno: 0,
		sessionId: '',
		msg: '',
		upReplyNo: null,
		updateMsg: {}
	}),
	actions:{
		toggleUpdate(no, msg) {
			this.upReplyNo = this.upReplyNo === no ? null : no
			this.updateMsg[no] = msg
		},
		async replyListData(bno) {
			this.bno = bno
			
			const {data} = await axios.get('/reply/list_vue/', {
				params: {
					bno: this.bno
				}
			})
				console.log(data)
				this.list = data.list
				this.count = data.count
			
		},
		async replyInsert(msgRef){
			if (!this.msg.trim) {
				msgRef.focus()
				return
			}
			const {data} = await axios.post('/reply/insert_vue/', {
				bno: this.bno,
				msg: this.msg
			})
				console.log(data)
				this.list = data.list
				this.count = data.count
				this.msg = ''
			
		} ,
		async replyDelete(no){
			const {data} = await axios.delete('/reply/delete_vue/',{
				params: {
					bno: this.bno,
					no: no
				}

			})
				console.log(data)
				this.list = data.list
				this.count = data.count
		},
		async replyUpdate(no){
			const {data} = await axios.put('/reply/update_vue/', {
				no: no,
				bno: this.bno,
				msg: this.updateMsg[no]
			})
				this.list = data.list
				this.upReplyNo = null
				this.count=data.count
			
		}
		
	}
	
})