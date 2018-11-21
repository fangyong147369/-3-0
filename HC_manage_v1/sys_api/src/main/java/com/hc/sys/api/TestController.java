//package com.zc.sys.api.zc;
//   public   List<User> list=new ArrayList<User>();
//   boolean b=false;
//      if(list.isEmpty()){
//      System.out.println("---第一次访问---");
//      User user=new User();
//      user.setName(model.getName());
//      list.add(user);
//   }else{
//      for (User entity:list) {
//         System.out.println("---entity.getName()---"+entity.getName());
//         if(entity.getName().equals(model.getName())){
//            System.out.println("---entity.getName().equals(model.getName())---");
//            b=true;
//            break;
//         }
//      }
//      if(b){
//         return "falil!";
//      }
//      else{
//         System.out.println("---添加开始了---");
//         User user=new User();
//         user.setName(model.getName());
//         list.add(user);
//      }
//   }
//      System.out.println("--目前一共有用户：----"+list.size());