import React, {useEffect, useState} from 'react';
import logo from './logo.svg';
import './App.css';
import axios from "axios";
function App() {
  const  [connection, setConnection] = useState<string>('');
  const connectionTest =()=>{
    axios.get('http://localhost:4000/').then((response) => {
      setConnection(response.data);
    }).catch((errer)=>
    {setConnection(errer.message);})
  }

  useEffect(()=>{
connectionTest();
  },[]);
  return (
    <div className="App">
      <header className="App-header">
       <p>{connection}</p>
      </header>
    </div>
  );
}

export default App;
