 try (
        Connection con = ConMan.getConnection()
    ) {

      // code
   
    } catch (SQLException e) {
      processException(e);
    }
  }


      PreparedStatement stmt = ConMan.prepStat(con, Statements.DEF_FIND_USER_BY_NAME);
      stmt.setString(1, name);


      ResultSet rs = stmt.executeQuery();

 rs.getInt("id)
 rs.getString("name")
 rs.getString("birthday")
 rs.getString("nickname")
