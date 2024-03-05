import React,{ useEffect } from 'react'
import Displaynews from './Displaynews';
import './page.css'
import { useSelector, useDispatch } from 'react-redux';
import { fetchnews } from '../redux/seacrhslice';

 const Searchpage = ({wrd}) => {
   console.log(`${wrd} word`);
    const dispatch = useDispatch();
    const data = useSelector((state) => state.words);
    console.log(data);
    useEffect(() => {
      dispatch(fetchnews({wrd}));
    }, [wrd, dispatch]);
  
    if (Array.isArray(data)) {
      return <div>Loading...</div>;
    }
    return (
        
      <div>
          
          <div className='headlines'>
      News:
      <div>
      <ul>
      {data.articles.map((article) => {
                if (article.urlToImage) {
                  return (
                    <li key={article.id}>
                      <Displaynews article={article} />
                      <br /><br />
                    </li>
                  );
                }
                return null;
              })}
  </ul>
  
      </div>
    </div>
      </div>
    )
  
}
export default Searchpage