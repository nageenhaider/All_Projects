import React,{useState} from 'react'
import './Navbar1.css'
import { Link} from 'react-router-dom';
export const Navbar = () => {
   const [searchQuery, setSearchQuery] = useState('');

  const handleSearch = (event) => {
   console.log(` kjl ${searchQuery}`);
    if (searchQuery) {
      window.location.href = `/Searchpage?query=${searchQuery}`;
    }
  };
  return (
    <div className='nav-box' >
       <div className='logo'><div className='logo1'> <b> NEWS</b></div> </div>
    <ul>
   
    <li>
       <Link className='nav-link'to='/'>Home</Link>
       </li>
           <li>
           <Link className='nav-link' to='/general'>General</Link>
           </li>
          <li>
          <Link className='nav-link' to='/business'>Business</Link>
          </li>
          <li>
       <Link className='nav-link' to='/technology'>Technology</Link>
       </li>
           <li>
           <Link className='nav-link' to='/entertainment'>Entertainment</Link>
           </li>
          <li>
          <Link className='nav-link' to='/politics'>Politics</Link>
          </li>
          <li>
          <Link className='nav-link' to='/sports'>Sports</Link>
          </li>
          <li>
          <Link className='nav-link' to='/health'>Health</Link>
          </li>
       
     </ul>
     <div className="search">
     <form onSubmit={handleSearch}>
          <input
            type="text"
            placeholder="Search News"
            name="search"
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
          />
           <Link to={`/search/${searchQuery}`} className='search1'>
  <b>Search</b>
</Link>
          
        </form>
            </div>
     
  </div>
  )
}

