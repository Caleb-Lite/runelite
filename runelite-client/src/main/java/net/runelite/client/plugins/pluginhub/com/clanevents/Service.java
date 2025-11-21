package net.runelite.client.plugins.pluginhub.com.clanevents;

import lombok.Data;

import javax.swing.event.SwingPropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Data
public class Service {

    SwingPropertyChangeSupport propChangeFirer;
    Optional<List<List<Object>>> apiValues = Optional.empty();
    List<SheetValueRange> sheetValueRangeList = new ArrayList<>();
    State state = State.IDLE;
    public Service() {
        propChangeFirer = new SwingPropertyChangeSupport(this);
    }

    public void addListener(PropertyChangeListener prop) {
        propChangeFirer.addPropertyChangeListener(prop);
    }

    public void setState(State newState) {
        State oldVal = this.state;
        this.state = newState;
        propChangeFirer.firePropertyChange("state", oldVal, newState);
    }
    public void refreshData() {
        setState(State.LOADING);
        CompletableFuture.supplyAsync(() -> {
            try {
                List<SheetValueRange> googleValues = GoogleSheet.getValues();
                setSheetValueRangeList(googleValues);
            } catch (Exception e){
                e.printStackTrace();
                return State.ERROR;
            }
            return State.COMPLETED;
        }).handle((r, v) -> {
            setState(r);
            return null;
        });
    }

    public Optional<List<List<Object>>> getSheet(String header){
        return sheetValueRangeList.stream()
                .filter(s -> s.getSheet().equals(header))
                .findFirst()
                .map(m -> m.getValueRange().getValues());
    }
}
/*
 * Copyright (c) 2022, cmsu224 <https://github.com/cmsu224>
 * Copyright (c) 2022, Brianmm94 <https://github.com/Brianmm94>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */