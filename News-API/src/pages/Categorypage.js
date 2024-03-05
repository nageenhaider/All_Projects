import React,{ useEffect } from 'react'
import Displaynews from './Displaynews';
import './page.css'
import { useSelector, useDispatch } from 'react-redux';
import { fetchitems } from '../redux/categoryslice';


 const Categorypage = ({cat}) => {
  const dispatch = useDispatch();
  const data = useSelector((state) => state.items);
  console.log(data);
  useEffect(() => {
    dispatch(fetchitems({cat}));
  }, [cat, dispatch]);

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
export default Categorypage
