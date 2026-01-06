const {createApp, onMounted, ref} = Vue
    	const {createPinia} = Pinia
    	
    	const jejuApp = createApp({
    		setup(){
    			// store 읽기
    			const store = useJejuStore()
    			// ref
    			const findRef = ref('관광지')
				const selectRef = ref('12')
    			// onMounted
    			onMounted(()=>{
    				store.jejuFindData()
    			})
    			// return
    			return{
    				store,
    				findRef,
					selectRef
    			}
    		}
    	})
    	jejuApp.use(createPinia())
    	jejuApp.mount('#jeju_find')