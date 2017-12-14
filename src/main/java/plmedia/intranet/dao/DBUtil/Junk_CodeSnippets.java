 try (
        Connection con = ConMan.getConnection()
    ) {

      // code
   
    } catch (SQLException e) {
      processException(e);
    }
  }


      PreparedStatement stmt = ConMan.prepStat(con, Statements.SQL);


      stmt.setString(1, "name");


      ResultSet rs = stmt.executeQuery();

