export default function Display({humidity,temp,weather,dt}){
        const dateValue = new Date()
        const value = new Date(dt*1000)
    return(
    <>
    {dateValue.getDate()!==value.getDate()?null:
      value.getHours() ===10 
    ||value.getHours() ===16 
    ||value.getHours() ===20 
    ||value.getHours() ===23?
     <><br/>
     <h1>{value.getHours()===10?
     "아침":value.getHours()===16?
     "점심":value.getHours()===20?
     "저녁":"밤"} </h1>
     <h1>습도:{humidity}</h1>
     <h1>온도:{Math.round(temp)}</h1>
     {weather.map(({description,main,id}) => {return(
     <div key={id}>
     <h1 >{main}</h1>
     <h1 >{description}</h1>
     </div>)})}
     <br/></>:null}</>)}