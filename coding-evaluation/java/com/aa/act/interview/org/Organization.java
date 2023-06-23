package com.aa.act.interview.org;

import java.util.Optional;

public abstract class Organization {

    private Position root;
    
    public Organization() {
        root = createOrganization();
    }
    
    protected abstract Position createOrganization();
    
    /**
     * hire the given person as an employee in the position that has that title
     * 
     * @param person
     * @param title
     * @return the newly filled position or empty if no position has that title
     */
    private static int id = 1;
    


public Optional<Position> hire(Name person, String title) {
//Here is my Code
Position position = getPosition(root, title);

if (position.getTitle() == null) {

return Optional.empty();

}

position.setEmployee(Optional.of(new Employee(id++, person)));

return Optional.of(position);

}


private Position getPosition(Position position, String title) {

Position actualPosition = null;

if (title.equals(position.getTitle())) {

return position;

} else if (!position.getDirectReports().isEmpty()) {

for (Position p : position.getDirectReports()) {

actualPosition = getPosition(p, title);

if (actualPosition.getTitle() != null) {

return actualPosition;

}

}

}

return new Position(null);

}

    @Override
    public String toString() {
        return printOrganization(root, "");
    }
    
    private String printOrganization(Position pos, String prefix) {
        StringBuffer sb = new StringBuffer(prefix + "+-" + pos.toString() + "\n");
        for(Position p : pos.getDirectReports()) {
            sb.append(printOrganization(p, prefix + "  "));
        }
        return sb.toString();
    }
}
