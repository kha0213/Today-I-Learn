import dotenv from 'dotenv'
import path from 'path'
import express from 'express';
const router = express.Router();
dotenv.config()


const app = express();
app.listen(4000)

/* GET home page. */
router.get('/', (req, res, next) => {

  console.log('hello');
  res.render('index', {title: 'Express'})


});

module.exports = router;
